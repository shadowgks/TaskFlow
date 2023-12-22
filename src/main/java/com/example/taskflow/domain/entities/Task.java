package com.example.taskflow.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private LocalDate startDate;
    @ElementCollection
    private List<String> tags;
    private LocalDate endDate;
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
