package com.example.expense_tracking_service.service.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    private static final String USERNAME_ALREADY_EXISTS = "Username '%s' already exists";

    public UsernameAlreadyExistsException(String username) {
        super(String.format(USERNAME_ALREADY_EXISTS, username));
    }
}
