package com.example.expense_tracking_service.service.repository;

import com.example.expense_tracking_service.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository {
    User getUserById(UUID userId);
    void deleteUserById(UUID userId);
    User saveUser(User user);
    List<User> getAllUsers();
}
