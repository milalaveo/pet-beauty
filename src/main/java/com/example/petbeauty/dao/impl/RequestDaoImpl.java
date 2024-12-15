package com.example.petbeauty.dao.impl;

import com.example.petbeauty.connection.ConnectionPool;
import com.example.petbeauty.dao.RequestDao;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl implements RequestDao {
    private static final Logger logger = LogManager.getLogger(RequestDaoImpl.class);

    private static final String INSERT_REQUEST = "INSERT INTO requests (user_id, service_id, status, execution_date, execution_time) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_REQUEST = "UPDATE requests SET service_id = ?, status = ?, execution_date = ?, execution_time = ? WHERE id = ?";
    private static final String DELETE_REQUEST = "DELETE FROM requests WHERE id = ?";
    private static final String SELECT_REQUEST_BY_ID = "SELECT * FROM requests WHERE id = ?";
    private static final String SELECT_REQUESTS_BY_USER_ID = "SELECT * FROM requests WHERE user_id = ?";

    @Override
    public boolean saveRequest(Request request) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_REQUEST);
            preparedStatement.setInt(1, request.getUserId());
            preparedStatement.setInt(2, request.getServiceId());
            preparedStatement.setString(3, request.getStatus());
            preparedStatement.setDate(4, Date.valueOf(request.getExecutionDate()));
            preparedStatement.setTime(5, Time.valueOf(request.getExecutionTime()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error saving request: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean updateRequest(Request request) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_REQUEST);
            preparedStatement.setInt(1, request.getServiceId());
            preparedStatement.setString(2, request.getStatus());
            preparedStatement.setDate(3, Date.valueOf(request.getExecutionDate()));
            preparedStatement.setTime(4, Time.valueOf(request.getExecutionTime()));
            preparedStatement.setInt(5, request.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error updating request: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public boolean deleteRequest(int requestId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_REQUEST);
            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Error deleting request: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement);
        }
    }

    @Override
    public Request findRequestById(int requestId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_REQUEST_BY_ID);
            preparedStatement.setInt(1, requestId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractRequest(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException("Error finding request: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Request> findRequestsByUserId(int userId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Request> requests = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_REQUESTS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                requests.add(extractRequest(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding requests by user ID: " + e.getMessage(), e);
        } finally {
            closeResources(connection, preparedStatement, resultSet);
        }
        return requests;
    }

    private Request extractRequest(ResultSet resultSet) throws SQLException {
        return new Request(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("service_id"),
                resultSet.getString("status"),
                resultSet.getDate("execution_date").toLocalDate(),
                resultSet.getTime("execution_time").toLocalTime()
        );
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error closing ResultSet", e);
            }
        }
        closeResources(connection, preparedStatement);
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing PreparedStatement", e);
            }
        }
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
