package com.example.taskflow.dto.task.response;

import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.StatusTask;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TaskResponseDto(
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        StatusTask statusTask,
        Boolean changed,
        LocalDateTime createdAt,
        List<String>tags,
        User assignedTo,
        User user
) {

}
