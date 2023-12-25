package com.example.taskflow.dto.user.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record userSignUp(
        @NotNull(message = "The first name cannot be null")
        @NotBlank(message = "The first name cannot be blank")
        @Column(nullable = false)
        String firstName,

        @NotNull(message = "The last name cannot be null")
        @NotBlank(message = "The last name cannot be blank")
        String lastName,

        @NotNull(message = "The username of role cannot be null")
        @NotBlank(message = "The username of role cannot be blank")
        String username,

        @NotNull(message = "The email address cannot be null")
        @NotBlank(message = "The email address cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "The password cannot be null")
        @NotBlank(message = "The password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) { }
