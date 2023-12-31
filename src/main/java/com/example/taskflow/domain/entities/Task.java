package com.example.taskflow.domain.entities;

import com.example.taskflow.domain.enums.StatusTask;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private StatusTask statusTask;
    private Boolean changed;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ElementCollection
    private List<String> tags;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void setDefaultValue(){
        if(changed == null){
            changed = false;
        }
    }
}
