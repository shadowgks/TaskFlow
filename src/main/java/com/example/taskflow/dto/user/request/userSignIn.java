package com.example.taskflow.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record userSignIn(
        @NotNull(message = "The username of role cannot be null")
        @NotBlank(message = "The username of role cannot be blank")
        String username,
        @NotNull(message = "The password cannot be null")
        @NotBlank(message = "The password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) { }
