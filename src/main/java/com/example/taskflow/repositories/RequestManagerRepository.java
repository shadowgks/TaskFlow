package com.example.taskflow.repositories;
import com.example.taskflow.domain.entities.RequestManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestManagerRepository extends JpaRepository<RequestManager, Long> {
}
