package com.example.taskflow.services;

import com.example.taskflow.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task update(Task task);
    void delete(Task task);
    List<Task> getTasks();
}
