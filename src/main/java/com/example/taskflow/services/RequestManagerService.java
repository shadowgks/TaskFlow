package com.example.taskflow.services;

import com.example.taskflow.domain.entities.RequestManager;
import com.example.taskflow.domain.entities.User;

import java.util.List;

public interface RequestManagerService {
    List<RequestManager> createRequestManager(User user);
}
