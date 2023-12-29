package com.example.taskflow.repositories;

import com.example.taskflow.domain.entities.Task;
import com.example.taskflow.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);
    Optional<Task> findTaskByIdAndAssignedToAndUser(Long taskId, User assigned, User manager);
    Optional<Task> findTaskByIdAndUser(Long taskId, User manager);

}
