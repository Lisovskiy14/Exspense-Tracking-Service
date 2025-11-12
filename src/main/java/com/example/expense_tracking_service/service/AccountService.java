package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.domain.Account;
import com.example.expense_tracking_service.dto.account.TopUpAccountRequestDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(UUID id);
    void createAccount(UUID userId);
    Account topUpAccount(TopUpAccountRequestDto topUpAccountRequestDto);
    void registerAnExpense(UUID userId, double amount);
}
