package com.example.expense_tracking_service.service.exception;

public class AccountNotFoundException extends ResourceNotFoundException {
    private static final String ACCOUNT_WITH_ID_NOT_FOUND = "Account with id '%s' not found";

    public AccountNotFoundException(String id) {
        super(String.format(ACCOUNT_WITH_ID_NOT_FOUND, id));
    }
}
