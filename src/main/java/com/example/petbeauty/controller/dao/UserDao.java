package com.example.petbeauty.controller.dao;

import com.example.petbeauty.controller.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    User findByUsername(String username);
    List<User> findAllUsers();
}