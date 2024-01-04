package com.example.taskflow.services.impls;

import com.example.taskflow.domain.entities.RequestManager;
import com.example.taskflow.domain.entities.User;
import com.example.taskflow.domain.enums.Role;
import com.example.taskflow.domain.enums.TokenStatus;
import com.example.taskflow.repositories.RequestManagerRepository;
import com.example.taskflow.repositories.TaskRepository;
import com.example.taskflow.repositories.UserRepository;
import com.example.taskflow.services.RequestManagerService;
import com.example.taskflow.services.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RequestManagerService requestManagerService;

    @Override
    public User register(User user) {
        checkUserIfExist(user);

        //hash password
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        User userSaved = userRepository.save(user);

        //check user if not admin
        if(!user.getRole().equals(Role.Admin)){
            requestManagerService.createRequestManager(user);
        }
        return userSaved;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }


    public void checkUserIfExist(User user){
        Optional<User> ifEmailExist = userRepository.findUserByEmail(user.getEmail());
        Optional<User> ifUserNameExist = userRepository.findUserByUserName(user.getUserName());

        if(ifEmailExist.isPresent()){
            throw new IllegalArgumentException("Email already exist!");
        } else if (ifUserNameExist.isPresent()) {
            throw new IllegalArgumentException("Username already exist!");
        }
    }

}
