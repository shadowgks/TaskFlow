package com.example.taskflow.dto.user.request;

import com.example.taskflow.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

@Builder
@Getter
public class RegisterDto {
        @NotNull(message = "The first name cannot be null")
        @NotBlank(message = "The first name cannot be blank")
        @Column(nullable = false)
        String firstName;
        @NotNull(message = "The last name cannot be null")
        @NotBlank(message = "The last name cannot be blank")
        String lastName;
        @NotNull(message = "The username of role cannot be null")
        @NotBlank(message = "The username of role cannot be blank")
        String username;
        @NotNull(message = "The email address cannot be null")
        @NotBlank(message = "The email address cannot be blank")
        @Email(message = "Invalid email format")
        String email;
        @NotNull(message = "The password cannot be null")
        @NotBlank(message = "The password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password;
        @NotNull(message = "The role cannot be null")
        Role role;

        public void setPassword(String password) {
                this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }

}


