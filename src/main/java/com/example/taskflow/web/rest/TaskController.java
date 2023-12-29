package com.example.taskflow.web.rest;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.domain.entities.User;
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

    @PostMapping("/create")
    public ResponseEntity<Response<TaskResponseDto>> createTask(@Valid
                                                                    @RequestBody TaskRequestDto taskRequestDto){
        Response<TaskResponseDto> response = new Response<>();
        Task task = taskService.create(TaskMapper.mapToEntity(taskRequestDto));
        response.setResult(TaskMapper.mapToDto(task));
        response.setMessage("Created Task Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/rejected")
    public ResponseEntity<Response<TaskResponseDto>> rejectedTask(@Valid
                                                                      @RequestParam String userAssignmentReq,
                                                                  @RequestParam String managerReq,
                                                                  @RequestParam Long taskIdReq){
        Response<TaskResponseDto> response = new Response<>();
        Task task = taskService.rejectedTask(userAssignmentReq, managerReq, taskIdReq);
        response.setResult(TaskMapper.mapToDto(task));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
