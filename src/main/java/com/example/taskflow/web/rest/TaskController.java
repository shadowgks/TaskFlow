package com.example.taskflow.web.rest;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.dto.task.request.TaskRequestDto;
import com.example.taskflow.dto.task.response.TaskResponseDto;
import com.example.taskflow.mapper.TaskMapper;
import com.example.taskflow.services.TaskService;
import com.example.taskflow.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0.0/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<Response<List<TaskResponseDto>>> getTasks(){
        Response<List<TaskResponseDto>> listResponse = new Response<>();
        List<Task> taskList = taskService.getTasks();
        listResponse.setResult(taskList
                .stream()
                .map(TaskMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(listResponse);
    }
    @GetMapping("{username}")
    public ResponseEntity<Response<List<TaskResponseDto>>> getTasksByUsername(@PathVariable("username") String username){
        Response<List<TaskResponseDto>> listResponse = new Response<>();
        List<Task> taskList = taskService.getTasksByUsername(username);
        listResponse.setMessage("Success, found all task "+username);
        listResponse.setResult(taskList
                .stream()
                .map(TaskMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(listResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<TaskResponseDto>> createTask(@Valid
                                                                    @RequestBody TaskRequestDto taskRequestDto){
        Response<TaskResponseDto> response = new Response<>();
        Task task = taskService.create(TaskMapper.mapToEntity(taskRequestDto));
        response.setResult(TaskMapper.mapToDto(task));
        response.setMessage("Created Task Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Response<TaskResponseDto>> updateTaskByUsername(@Valid
                                                                              @PathVariable("username") String username,
                                                                @RequestBody TaskRequestDto taskRequestDto){
        Response<TaskResponseDto> response = new Response<>();
        Task task = taskService.update(TaskMapper.mapToEntity(taskRequestDto), username);
        response.setResult(TaskMapper.mapToDto(task));
        response.setMessage("Update Task Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}/{idTask}")
    public ResponseEntity<Response<TaskResponseDto>> deleteTaskByUsername(@Valid
                                                                          @PathVariable("username") String username,
                                                                          @PathVariable("idTask") Long idTask){
        Response<TaskResponseDto> response = new Response<>();
        taskService.delete(idTask, username);
        response.setMessage("Deleted Task Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{rejectedOrDeletedOrAssignment}")
    public ResponseEntity<Response<TaskResponseDto>> rejectedOrDeletedTask(@Valid @PathVariable("rejectedOrDeletedOrAssignment") 
                                                                               String rejectedOrDeletedOrAssignment,
                                                                  @RequestParam String userAssignmentReq,
                                                                  @RequestParam String managerReq,
                                                                  @RequestParam Long taskIdReq){
        Response<TaskResponseDto> response = new Response<>();
        switch (rejectedOrDeletedOrAssignment) {
            case "rejected" -> {
                Task task = taskService.rejectedTaskByToken(taskIdReq, userAssignmentReq, managerReq);
                response.setResult(TaskMapper.mapToDto(task));
                response.setMessage("Success, Rejected task assigment");
            }
            case "deleted" -> {
                taskService.deletedTaskByToken(taskIdReq, userAssignmentReq, managerReq);
                response.setMessage("Success, Deleted task assigment");
            }
            case "assignmentToUser" -> {
                Task task = taskService.assignmentToUser(taskIdReq, userAssignmentReq, managerReq);
                response.setResult(TaskMapper.mapToDto(task));
                response.setMessage("Success, Assignment task to another user");
            }
            default -> response.setError("Sorry, you have two choices: [Rejected, Deleted, assignmentToUser].");
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
