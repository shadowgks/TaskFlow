package com.example.taskflow.services;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    List<Task> getTasksByUsername(String username);
    Task create(Task task);
    Task update(Task task, String username);
    void delete(Long taskId, String username);
    Task rejectedTaskByToken(Long taskIdP, String userAssignmentP, String managerP);
    void deletedTaskByToken(Long taskIdP, String userAssignmentP, String managerP);
    Task assignmentToUser(Long taskIdP, String userAssignmentP, String managerP);
}
