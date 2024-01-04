package com.example.taskflow.dto.user.response;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.domain.enums.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        String email,
        String userName,
        String firstName,
        String lastName,
        String password,
        Role role,
        List<Task> tasks
) { }
