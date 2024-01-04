package com.example.taskflow.dto.task.request;

import com.example.taskflow.domain.entities.User;
import jakarta.validation.constraints.NotNull;

public record AssignedTaskToUserDto(
        @NotNull(message = "Username the manager cannot be null")
        User userNameManager,
        @NotNull(message = "You must enter the username of the user to assign them the task")
        User assignedTo
) {
}
