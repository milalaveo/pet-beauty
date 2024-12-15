package com.example.petbeauty.service.impl;

import com.example.petbeauty.dao.UserDao;
import com.example.petbeauty.dao.impl.UserDaoImpl;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.User;
import com.example.petbeauty.service.MailService;
import com.example.petbeauty.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean verifyLogin(String username, String password) throws ServiceException {
        User user;
        try {
            user = userDao.findByUsername(username);

            if (user == null) {
                return false;
            }

            if (user.getVerificationCode() != null) {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while trying to login", e);
        }

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean registerUser(String username, String password, String firstname, String lastname, String email) throws ServiceException {
        String encryptedPassword = encoder.encode(password);

        User user = new User(username, encryptedPassword, firstname, lastname, email);

        String verificationCode = UUID.randomUUID().toString();
        user.setVerificationCode(verificationCode);

        try {
            boolean registered = userDao.saveUser(user);

            if (registered) {
                MailService.sendVerificationEmail(user.getEmail(), verificationCode);
            }

            return registered;
        } catch (DaoException | SQLException e) {
            throw new ServiceException("Error registering user", e);
        }
    }

    @Override
    public boolean confirmSignup(String verificationCode) throws ServiceException {
        User user;

        try {
            user = userDao.findByVerificationCode(verificationCode);
            user.setVerificationCode(null); // no verification code -> verified

            return userDao.updateUser(user);
        } catch (DaoException | SQLException e) {
            throw new ServiceException("Error while trying to verify", e);
        }
    }

    @Override
    public User findUser(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding user", e);
        }
    }
}