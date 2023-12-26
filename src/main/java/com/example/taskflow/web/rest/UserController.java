package com.example.taskflow.web.rest;

import com.example.taskflow.domain.entities.User;
import com.example.taskflow.dto.user.response.UserResponse;
import com.example.taskflow.mapper.UserMapper;
import com.example.taskflow.services.UserService;
import com.example.taskflow.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0.0/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<Response<List<UserResponse>>> getAllUser(){
        Response<List<UserResponse>> listResponse = new Response<>();
        List<User> userList = userService.getAllUser();
        listResponse.setResult(userList
                .stream()
                .map(UserMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(listResponse);
    }

//    @PostMapping("/create")
//    public ResponseEntity<Response<UserResponse>> registerUser(@Valid @RequestBody User        )
}
