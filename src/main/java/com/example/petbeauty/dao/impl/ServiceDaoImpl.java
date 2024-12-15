package com.example.petbeauty.dao.impl;

import com.example.petbeauty.connection.ConnectionPool;
import com.example.petbeauty.dao.ServiceDao;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {
    private static final Logger logger = LogManager.getLogger(ServiceDaoImpl.class);

    private static final String INSERT_SERVICE = "INSERT INTO services (name, description, price) VALUES (?, ?, ?)";
    private static final String UPDATE_SERVICE = "UPDATE services SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_SERVICE = "DELETE FROM services WHERE id = ?";
    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM services WHERE id = ?";
    private static final String SELECT_ALL_SERVICES = "SELECT * FROM services";

    @Override
    public boolean saveService(Service service) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_SERVICE);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setBigDecimal(3, service.getPrice());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error saving service: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean updateService(Service service) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SERVICE);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setBigDecimal(3, service.getPrice());
            preparedStatement.setInt(4, service.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error updating service: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean deleteService(int serviceId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SERVICE);
            preparedStatement.setInt(1, serviceId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error deleting service: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public Service findServiceById(int serviceId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SERVICE_BY_ID);
            preparedStatement.setInt(1, serviceId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Service(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException("Error finding service by ID: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Service> findAllServices() throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Service> services = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SERVICES);

            while (resultSet.next()) {
                services.add(new Service(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving all services: " + e.getMessage(), e);
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return services;
    }

    @Override
    public List<Service> getServicesForUser(int userId) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Service> services = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SERVICES);

            while (resultSet.next()) {
                services.add(new Service(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving all services: " + e.getMessage(), e);
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return services;
    }

    private void closeResources(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error while closing statement", e);
            }
        }
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error while closing result set", e);
            }
        }
        closeResources(connection, statement);
    }
}
