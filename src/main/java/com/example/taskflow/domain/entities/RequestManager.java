package com.example.taskflow.domain.entities;


import com.example.taskflow.domain.enums.TokenStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RequestManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberChoice;
    private LocalDateTime dateChoice;
    private TokenStatus tokenStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

