package com.example.petbeauty.dao.impl;


import com.example.petbeauty.connection.ConnectionPool;
import com.example.petbeauty.dao.UserDao;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final String INSERT_USER = "INSERT INTO users (username, password, firstname, lastname, email, verification_code) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users set username = ?, password = ?, firstname = ?, lastname = ?, email = ?, verification_code = ? WHERE id = ?";
    private static final String FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String FIND_BY_VERIFICATION_CODE = "SELECT * FROM users WHERE verification_code = ?";

    @Override
    public boolean saveUser(User user) throws DaoException {
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getVerificationCode());
            preparedStatement.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(connection);

            return true;
        } catch (SQLException e) {
            throw new DaoException("Error saving user: " + e.getMessage(), e);
        }
    }

    public boolean updateUser(User user) throws DaoException {
        PreparedStatement preparedStatement;
        Connection connection;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getVerificationCode());
            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(connection);

            return true;
        } catch (SQLException e) {
            throw new DaoException("Error updating user: " + e.getMessage(), e);
        }
    }

    @Override
    public User findByUsername(String username) throws DaoException {
        try {
            return findUser(FIND_BY_USERNAME, username);
        } catch (SQLException e) {
            throw new DaoException("Error finding user by username: " + e.getMessage(), e);
        }
    }

    @Override
    public User findByVerificationCode(String verificationCode) throws DaoException {
        try {
            return findUser(FIND_BY_VERIFICATION_CODE, verificationCode);
        } catch (SQLException e) {
            throw new DaoException("Error finding user by username: " + e.getMessage(), e);
        }
    }

    private User findUser(String query, String value) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, value);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;

        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("verification_code")
            );
        }

        ConnectionPool.getInstance().releaseConnection(connection);

        return user;
    }
}
