package com.example.taskflow.services;

import com.example.taskflow.domain.entities.User;

public interface UserService {
    User getByEmail(String email);
    User getByUsername(String username);
    User create(User user);
}
