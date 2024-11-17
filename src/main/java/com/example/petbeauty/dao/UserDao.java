package com.example.petbeauty.dao;

import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.User;

import java.sql.SQLException;

public interface UserDao {
    boolean saveUser(User user) throws DaoException, SQLException;
    boolean updateUser(User user) throws DaoException, SQLException;
    User findByUsername(String username) throws DaoException;
    User findByVerificationCode(String verificationCode) throws DaoException;
}