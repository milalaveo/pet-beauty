package com.example.petbeauty.controller.service;

import com.example.petbeauty.controller.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    User findUserByUsername(String username);
    List<User> getAllUsers();
}