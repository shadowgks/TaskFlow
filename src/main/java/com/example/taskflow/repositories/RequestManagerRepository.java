package com.example.taskflow.repositories;
import com.example.taskflow.domain.entities.RequestManager;
import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestManagerRepository extends JpaRepository<RequestManager, Long> {
    Optional<RequestManager> findRequestManagerByUserAndTokenStatus(User user, TokenStatus tokenStatus);
}
