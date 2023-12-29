package com.example.taskflow.services.impls;

import com.example.taskflow.domain.entities.RequestManager;
import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.TokenStatus;
import com.example.taskflow.repositories.RequestManagerRepository;
import com.example.taskflow.services.RequestManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestManagerServiceImpl implements RequestManagerService {
    private final RequestManagerRepository requestManagerRepository;

    @Override
    public List<RequestManager> createRequestManager(User user) {
        List<RequestManager> requestsManager = new ArrayList<>();
        requestsManager.add(createRequestsManagers(user, TokenStatus.Deleted));
        requestsManager.add(createRequestsManagers(user, TokenStatus.Rejected));
        return requestManagerRepository.saveAll(requestsManager);
    }

    public RequestManager createRequestsManagers(User user, TokenStatus tokenStatus){
        return RequestManager.builder()
                .numberChoice(0)
                .tokenStatus(tokenStatus)
                .user(user)
                .build();
    }
}
