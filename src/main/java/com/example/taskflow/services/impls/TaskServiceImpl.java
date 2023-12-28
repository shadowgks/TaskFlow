package com.example.taskflow.services.impls;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.Role;
import com.example.taskflow.repositories.TaskRepository;
import com.example.taskflow.repositories.UserRepository;
import com.example.taskflow.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Task> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            throw new IllegalArgumentException("Not found any tasks!");
        }
        return taskList;
    }

    @Override
    public Task create(Task task) {
        validateDateTask(task);
        User user = checkUserIfExist(task.getUser().getUserName(),
                "This is user not exist!");
        task.setUser(user);
        if(task.getAssignedTo() != null){
            User userAssigned = checkUserIfExist(task.getAssignedTo().getUserName(),
                    "This user you're trying to assign does not exist!");
            validateAssigment(user, userAssigned);
            task.setAssignedTo(userAssigned);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public void delete(Task task) {

    }


    //Validate!
    public void validateDateTask(Task task){
        if(task.getStartDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Date has moved beyond this date now!");
        }
    }

    public User checkUserIfExist(String username, String errorMessage){
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    public void validateAssigment(User user, User userAssigned){
        if(!user.getRole().equals(Role.Admin)){
            throw new IllegalArgumentException("Sorry, you cannot be assigned because you are not an admin!");
        }else if(user.equals(userAssigned)){
            throw new IllegalArgumentException("Oops, something's wrong!");
        }
    }
}
