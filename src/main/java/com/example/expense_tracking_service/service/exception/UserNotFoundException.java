package com.example.expense_tracking_service.service.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    private static final String USER_WITH_ID_IS_NOT_FOUND = "User with id %s is not found";

    public UserNotFoundException(String id) {
        super(String.format(USER_WITH_ID_IS_NOT_FOUND, id));
    }
}
