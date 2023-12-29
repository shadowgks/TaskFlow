package com.example.taskflow.services;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    List<Task> getTasksByUsername(String username);
    Task create(Task task);
    Task update(String username, Task task);
    void delete(Task task);
    Task rejectedTask(Long taskIdP, String userAssignmentP, String managerP);
    void deletedTask(Long taskIdP, String userAssignmentP, String managerP);
    Task assignmentToUser(Long taskIdP, String userAssignmentP, String managerP);
}
