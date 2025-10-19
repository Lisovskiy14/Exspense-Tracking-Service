package com.example.expense_tracking_service.service.repository.impl;

import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.service.exception.UserNotFoundException;
import com.example.expense_tracking_service.service.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final Map<UUID, User> users = new HashMap<>();

    @Override
    public User getUserById(UUID userId) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException(userId.toString());
        }
        return users.get(userId);
    }

    @Override
    public void deleteUserById(UUID userId) {
        users.remove(userId);
    }

    @Override
    public User saveUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }
}
