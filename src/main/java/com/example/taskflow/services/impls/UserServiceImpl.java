package com.example.taskflow.services.impls;

import com.example.taskflow.domain.entities.User;
import com.example.taskflow.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User register(User user) {

        return null;
    }
}
