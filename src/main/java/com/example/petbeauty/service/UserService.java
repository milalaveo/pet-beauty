package com.example.petbeauty.service;

import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.User;

public interface UserService {
    boolean verifyLogin(String username, String password) throws ServiceException;
    boolean registerUser(String username, String password, String firstname, String lastname, String email) throws ServiceException;
    boolean confirmSignup(String verificationCode) throws ServiceException;
    User findUser(String username) throws ServiceException;
}