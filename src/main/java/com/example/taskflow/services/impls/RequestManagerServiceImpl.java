package com.example.taskflow.services.impls;

import com.example.taskflow.domain.entities.RequestManager;
import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.TokenStatus;
import com.example.taskflow.repositories.RequestManagerRepository;
import com.example.taskflow.services.RequestManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public RequestManager userIfExistOnRequestManager(User userAssigment, TokenStatus tokenStatus){
        return requestManagerRepository.findRequestManagerByUserAndTokenStatus(userAssigment, tokenStatus)
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this user not exist on request manager"));
    }

    public void checkRejectedIfBigThen2(User userAssigment){
        RequestManager userIfExistOnRequestManager = userIfExistOnRequestManager(userAssigment, TokenStatus.Rejected);
        //AtomicReference For Increment
        AtomicReference<Integer> numberChoice = new AtomicReference<>(userIfExistOnRequestManager.getNumberChoice());
        if(numberChoice.get() >= 2){
            throw new IllegalArgumentException("Sorry, can't rejected this task because you exceeded the specified number of rejected!");
        }
        userIfExistOnRequestManager.setDateChoice(LocalDateTime.now());
        userIfExistOnRequestManager.setNumberChoice(numberChoice.get()+1);
        requestManagerRepository.save(userIfExistOnRequestManager);
    }

    public void checkDeletedIfBigThen1(User userAssigment){
        RequestManager userIfExistOnRequestManager = userIfExistOnRequestManager(userAssigment, TokenStatus.Deleted);
        //AtomicReference For Increment
        AtomicReference<Integer> numberChoice = new AtomicReference<>(userIfExistOnRequestManager.getNumberChoice());
        if(numberChoice.get() >= 1){
            throw new IllegalArgumentException("Sorry, can't deleted this task because you exceeded the specified number of deleted!");
        }
        userIfExistOnRequestManager.setDateChoice(LocalDateTime.now());
        userIfExistOnRequestManager.setNumberChoice(numberChoice.get()+1);
        requestManagerRepository.save(userIfExistOnRequestManager);
    }

}
