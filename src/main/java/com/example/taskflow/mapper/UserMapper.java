package com.example.taskflow.mapper;

import com.example.taskflow.domain.entities.User;
import com.example.taskflow.dto.user.response.UserResponse;

public class UserMapper {
    public static UserResponse mapToDto(User user){
        return UserResponse.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .tasks(user.getTasks())
                .build();
    }
}
