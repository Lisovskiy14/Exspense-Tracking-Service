package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<UUID, User> users = new HashMap<>();

    public User getUserById(UUID userId) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException(userId.toString());
        }
        return users.get(userId);
    }

    public void deleteUserById(UUID userId) {
        users.remove(userId);
    }

    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        users.put(user.getId(), user);
        return user;
    }

    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }
}
