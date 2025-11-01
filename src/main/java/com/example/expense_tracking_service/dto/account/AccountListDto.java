package com.example.expense_tracking_service.dto.account;

import lombok.Value;

import java.util.List;

@Value
public class AccountListDto {
    List<AccountDto> accounts;
}
