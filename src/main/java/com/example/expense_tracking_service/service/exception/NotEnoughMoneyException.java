package com.example.expense_tracking_service.service.exception;

public class NotEnoughMoneyException extends RuntimeException {
    private static final String NOT_ENOUGH_MONEY_ON_ACCOUNT_WITH_ID = "Not enough money on account with id '%s'";

    public NotEnoughMoneyException(String id) {
        super(String.format(NOT_ENOUGH_MONEY_ON_ACCOUNT_WITH_ID, id));
    }
}
