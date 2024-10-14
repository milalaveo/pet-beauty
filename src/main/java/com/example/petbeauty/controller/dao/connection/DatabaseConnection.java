package com.example.petbeauty.controller.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = PropertyReaderUtil.getProperty("url");
    private static final String USER = PropertyReaderUtil.getProperty("user");
    private static final String PASSWORD = PropertyReaderUtil.getProperty("password");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}