package com.example.taskflow.mapper;


import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.dto.task.response.TaskResponseDto;

public class TaskMapper {
    public static TaskResponseDto mapToDto(Task task){
        return TaskResponseDto.builder()
                .name(task.getName())
                .statusTask(task.getStatusTask())
                .endDate(task.getEndDate())
                .assignedTo(task.getAssignedTo())
                .tags(task.getTags())
                .changed(task.getChanged())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .startDate(task.getStartDate())
                .user(task.getUser())
                .build();
    }

    public static Task mapToEntity(TaskResponseDto taskResponseDto){
        return Task.builder()
                .name(taskResponseDto.name())
                .statusTask(taskResponseDto.statusTask())
                .endDate(taskResponseDto.endDate())
                .assignedTo(taskResponseDto.assignedTo())
                .tags(taskResponseDto.tags())
                .changed(taskResponseDto.changed())
                .description(taskResponseDto.description())
                .completed(taskResponseDto.completed())
                .createdAt(taskResponseDto.createdAt())
                .startDate(taskResponseDto.startDate())
                .user(taskResponseDto.user())
                .build();
    }
}
