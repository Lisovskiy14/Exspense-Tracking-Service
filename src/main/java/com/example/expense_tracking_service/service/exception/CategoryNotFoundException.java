package com.example.expense_tracking_service.service.exception;

public class CategoryNotFoundException extends RuntimeException {
    private static final String CATEGORY_WITH_ID_IS_NOT_FOUND = "Category with id %s is not found";

    public CategoryNotFoundException(String id) {
        super(String.format(CATEGORY_WITH_ID_IS_NOT_FOUND, id));
    }
}
