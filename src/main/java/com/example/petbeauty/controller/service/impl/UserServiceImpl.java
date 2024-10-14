package com.example.petbeauty.controller.service.impl;

import com.example.petbeauty.controller.dao.UserDao;
import com.example.petbeauty.controller.dao.impl.UserDaoImpl;
import com.example.petbeauty.controller.model.User;
import com.example.petbeauty.controller.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void registerUser(User user) {
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.saveUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }
}