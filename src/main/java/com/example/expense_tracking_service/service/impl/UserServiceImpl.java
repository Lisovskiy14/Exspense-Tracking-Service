package com.example.expense_tracking_service.service.impl;

import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.service.UserService;
import com.example.expense_tracking_service.service.exception.UserNotFoundException;
import com.example.expense_tracking_service.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountServiceImpl accountServiceImpl;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }
        return user.get();
    }

    @Override
    public User createUser(User user) {
        user = userRepository.save(user);
        accountServiceImpl.createAccount(user.getId());
        return user;
    }

    @Override
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }
}
