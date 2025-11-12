package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID userId);
    User createUser(User user);
    void deleteUserById(UUID userId);
}
