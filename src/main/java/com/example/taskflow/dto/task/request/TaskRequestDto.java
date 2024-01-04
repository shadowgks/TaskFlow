package com.example.taskflow.dto.task.request;

import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.StatusTask;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record TaskRequestDto(
        Long id,
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @FutureOrPresent(message = "Start date should be in the present or future")
        LocalDateTime startDate,
        @Future(message = "End date should be in the future")
        LocalDateTime endDate,
        @NotNull(message = "You must be selected status task")
        StatusTask statusTask,
        @NotEmpty(message = "Tags list cannot be empty")
        List<String> tags,
        @NotNull(message = "user cannot be null")
        User user,
        User assignedToUser,
        LocalDateTime createdAt
) {
}
