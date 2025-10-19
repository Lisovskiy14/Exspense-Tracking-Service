package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteUserById(userId);
    }

    public User saveUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
