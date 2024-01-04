package com.example.taskflow.mapper;


import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.dto.task.request.TaskRequestDto;
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
                .createdAt(task.getCreatedAt())
                .startDate(task.getStartDate())
                .user(task.getUser())
                .build();
    }

    public static Task mapToEntity(TaskRequestDto taskRequestDto){
        return Task.builder()
                .id(taskRequestDto.id())
                .name(taskRequestDto.name())
                .statusTask(taskRequestDto.statusTask())
                .endDate(taskRequestDto.endDate())
                .user(taskRequestDto.user())
                .assignedTo(taskRequestDto.assignedToUser())
                .tags(taskRequestDto.tags())
                .description(taskRequestDto.description())
                .createdAt(taskRequestDto.createdAt())
                .startDate(taskRequestDto.startDate())
                .build();
    }
}
