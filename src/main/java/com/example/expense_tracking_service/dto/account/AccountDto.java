package com.example.expense_tracking_service.dto.account;

import lombok.Value;


@Value
public class AccountDto {
    String userId;
    double balance;
}
