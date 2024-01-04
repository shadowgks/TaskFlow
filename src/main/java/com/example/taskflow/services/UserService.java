package com.example.taskflow.services;

import com.example.taskflow.domain.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getByEmail(String email);
    User getByUsername(String username);
    User register(User user);
}
