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
    private final RequestManagerServiceImpl requestManagerServiceImpl;

    @Override
    public List<Task> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            throw new IllegalArgumentException("Not found any tasks!");
        }
        return taskList;
    }

    public List<Task> getTasksByUsername(String username){
        User user = checkUserIfExist(username,
                "This is user not exist!");
        List<Task> taskList = taskRepository.findAllByUser(user);
        if(taskList.isEmpty()){
            throw new IllegalArgumentException("Not found any tasks! "+ username);
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
    public Task update(Task task, String username) {
        Task checkTaskIfExistByUser = checkTaskIfExistByUser(task.getId(), username, "this is task '"+task.getName()+"' not found in the user "+ username);
        if(checkTaskIfExistByUser.getStartDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("You can't updated this task because task has already done!");
        }
        checkTaskIfExistByUser.setName(task.getName());
        checkTaskIfExistByUser.setDescription(task.getDescription());
        checkTaskIfExistByUser.setTags(task.getTags());
        checkTaskIfExistByUser.setStartDate(task.getStartDate());
        checkTaskIfExistByUser.setEndDate(task.getEndDate());
        checkTaskIfExistByUser.setStatusTask(task.getStatusTask());
        return taskRepository.save(checkTaskIfExistByUser);
    }

    @Override
    public void delete(Long taskId, String username) {
        Task checkTaskIfExistByUser = checkTaskIfExistByUser(taskId, username, "this is task '"+taskId+"' not found in the user "+ username);
        taskRepository.delete(checkTaskIfExistByUser);
    }

    @Override
    public Task rejectedTaskByToken(Long taskIdP, String userAssignmentP, String managerP) {
        Task checkTaskIfExistByAssigmentAndManager = validRejectOrDeleted(taskIdP, userAssignmentP, managerP, "rejected");
        return taskRepository.save(checkTaskIfExistByAssigmentAndManager);
    }

    @Override
    public void deletedTaskByToken(Long taskIdP, String userAssignmentP, String managerP) {
        Task checkTaskIfExistByAssigmentAndManager = validRejectOrDeleted(taskIdP, userAssignmentP, managerP, "deleted");
        taskRepository.delete(checkTaskIfExistByAssigmentAndManager);
    }

    @Override
    public Task assignmentToUser(Long taskIdP, String userAssignmentP, String managerP) {
        User userAssigment = checkUserIfExist(userAssignmentP,
                "This is user assignment not exist!");
        Task checkTaskIfExistByManager =
                checkTaskIfExistByUser(taskIdP, managerP, "No tasks found for this manager");
        //check task if complete
        checkTaskIfComplete(checkTaskIfExistByManager);
        //check task if changed
        checkTaskIfExistByManager.setAssignedTo(userAssigment);
        return taskRepository.save(checkTaskIfExistByManager);
    }


    //validate!
    public void validateDateTask(Task task){
        if(task.getStartDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Date has moved beyond this date now!");
        }
    }

    public Task checkTaskIfExistByUser(Long task, String username, String message){
        User user = checkUserIfExist(username,
                "This is user not exist! "+username);
        return taskRepository.findTaskByIdAndUser(task, user)
                .orElseThrow(() -> new IllegalArgumentException(message));
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

    public void checkTaskIfComplete(Task task){
        if(task.getStartDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("You can't deleted this task because task has already done!");
        }
    }

    public Task validRejectOrDeleted(Long taskIdP, String userAssignmentP, String managerP, String status){
        User userAssigment = checkUserIfExist(userAssignmentP,
                "This is user assignment not exist!");
        User manager = checkUserIfExist(managerP,
                "This is manager not exist!");
        Task checkTaskIfExistByAssigmentAndManager = taskRepository.findTaskByIdAndAssignedToAndUser(taskIdP, userAssigment, manager)
                .orElseThrow(() -> new IllegalArgumentException("Not found this task assigment"));
        //check task if complete
        checkTaskIfComplete(checkTaskIfExistByAssigmentAndManager);
        //check task if changed
        if(checkTaskIfExistByAssigmentAndManager.getChanged().equals(true)){
            throw new IllegalArgumentException("You can't reject or deleted this task because it has already been assigned to another user who has already rejected it!");
        }

        switch (status) {
            case "rejected" -> {
                checkTaskIfExistByAssigmentAndManager.setAssignedTo(null);
                checkTaskIfExistByAssigmentAndManager.setChanged(true);
                requestManagerServiceImpl.checkRejectedIfBigThen2(userAssigment);
            }
            case "deleted" -> requestManagerServiceImpl.checkDeletedIfBigThen1(userAssigment);
            default -> throw new IllegalArgumentException("Sorry, not found any status like this: "+status);
        }

        return checkTaskIfExistByAssigmentAndManager;
    }
}
