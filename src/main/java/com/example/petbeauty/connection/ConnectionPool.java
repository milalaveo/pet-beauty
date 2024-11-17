package com.example.petbeauty.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static final String URL = PropertyReaderUtil.getProperty("db.url");
    private static final String USER = PropertyReaderUtil.getProperty("db.user");
    private static final String PASSWORD = PropertyReaderUtil.getProperty("db.password");
    private static final Lock lock = new ReentrantLock();
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int CONNECTION_POOL_CAPACITY = 2;
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_CAPACITY);
    private final BlockingQueue<Connection> usedConnections = new LinkedBlockingQueue<>(CONNECTION_POOL_CAPACITY);

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            logger.fatal("Failed to register driver.", e);
            throw new ExceptionInInitializerError("Failed to register database driver.");
        }
    }

    private ConnectionPool() {
        Connection connection = null;
        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                logger.debug("Connection #{} created.", i);
            } catch (SQLException e) {
                logger.fatal("Failed to create connection.", e);
            }
            if (connection == null) {
                logger.error("Failed to add connection to the pool.");
            } else {
                freeConnections.add(connection);
                logger.debug("Connection #{} added to the pool.", i);
            }
        }
    }

    public static ConnectionPool getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPool();
                logger.debug("Connection pool created.");
            }
        } finally {
            lock.unlock();
        }
        logger.debug("Existing connection pool returned.");
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            logger.debug(
                    "Connection taken from the pool. Available connections: {}",
                    (CONNECTION_POOL_CAPACITY - freeConnections.remainingCapacity())
            );
            usedConnections.put(connection);
            logger.debug("Connection put to the used connections queue.");
        } catch (InterruptedException e) {
            logger.error("Failed to get connection from the pool.", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!usedConnections.remove(connection)) {
                logger.warn("Connection not found in the used connections queue.");
            }
            logger.debug("Connection removed from the used connections queue.");
            freeConnections.put(connection);
            logger.debug(
                    "Connection put back to the pool. Available connections: {}",
                    (CONNECTION_POOL_CAPACITY - freeConnections.remainingCapacity())
            );
        } catch (InterruptedException e) {
            logger.error("Failed to release connection to the pool.", e);
        }
    }
}