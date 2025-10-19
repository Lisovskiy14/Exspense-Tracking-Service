package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.dto.user.UserRequest;
import com.example.expense_tracking_service.service.UserService;
import com.example.expense_tracking_service.web.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    public final UserService userService;
    public final UserMapper userMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMapper.toUserDto(
                        userService.getUserById(userId)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.saveUser(
                        userMapper.toUser(userRequest)));
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userMapper.toUserListDto(userService.getAllUsers()));
    }
}
