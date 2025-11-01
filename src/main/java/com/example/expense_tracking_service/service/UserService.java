package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.service.exception.UserNotFoundException;
import com.example.expense_tracking_service.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;

    public User getUserById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }
        return user.get();
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

    public User saveUser(User user) {
        user = userRepository.save(user);
        accountService.createAccount(user.getId());
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
