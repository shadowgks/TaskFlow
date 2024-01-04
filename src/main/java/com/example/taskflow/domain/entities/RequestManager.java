package com.example.taskflow.domain.entities;


import com.example.taskflow.domain.enums.TokenStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberChoice;
    private LocalDateTime dateChoice;
    @Enumerated(EnumType.STRING)
    private TokenStatus tokenStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

