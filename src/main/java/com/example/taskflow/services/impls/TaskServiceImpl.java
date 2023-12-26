package com.example.taskflow.services.impls;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.repositories.TaskRepository;
import com.example.taskflow.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            throw new IllegalArgumentException("Not found any tasks!");
        }
        return taskList;
    }
    @Override
    public Task add(Task task) {
        if(task.getStartDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Date has moved beyond this date now!");
        }
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public void delete(Task task) {

    }
}
