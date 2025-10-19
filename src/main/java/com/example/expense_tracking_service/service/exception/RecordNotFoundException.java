package com.example.expense_tracking_service.service.exception;

public class RecordNotFoundException extends RuntimeException {
    private static final String RECORD_WITH_ID_IS_NOT_FOUND = "Record with id %s is not found";

    public RecordNotFoundException(String id) {
        super(String.format(RECORD_WITH_ID_IS_NOT_FOUND, id));
    }
}
