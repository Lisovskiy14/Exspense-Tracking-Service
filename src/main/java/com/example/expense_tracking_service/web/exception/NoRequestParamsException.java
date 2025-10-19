package com.example.expense_tracking_service.web.exception;

public class NoRequestParamsException extends RuntimeException {
    private static final String NO_PARAMS_FOUND = "No request params found";

    public NoRequestParamsException() {
        super(NO_PARAMS_FOUND);
    }
}
