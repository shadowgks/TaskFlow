package com.example.taskflow.dto.task.request;

import com.example.taskflow.domain.enums.StatusTask;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record TaskRequestDto(
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
        @NotNull(message = "Completed status cannot be null")
        Boolean completed,
        @NotNull(message = "Changed status cannot be null")
        Boolean changed,
        @NotEmpty(message = "Tags list cannot be empty")
        List<String> tags,
        @NotNull(message = "AssignedToId cannot be null")
        Long assignedToId,
        @NotNull(message = "UserId cannot be null")
        Long userId,
        LocalDateTime createdAt
) {
}
