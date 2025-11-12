package com.example.expense_tracking_service.service.impl;

import com.example.expense_tracking_service.domain.Account;
import com.example.expense_tracking_service.domain.User;
import com.example.expense_tracking_service.dto.account.TopUpAccountRequestDto;
import com.example.expense_tracking_service.service.AccountService;
import com.example.expense_tracking_service.service.exception.NotEnoughMoneyException;
import com.example.expense_tracking_service.service.repository.AccountRepository;
import com.example.expense_tracking_service.service.exception.AccountNotFoundException;
import com.example.expense_tracking_service.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(UUID id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotFoundException(id.toString());
        }
        return account.get();
    }

    @Override
    public void createAccount(UUID userId) {
        User userProxy = userRepository.getReferenceById(userId);

        Account account = Account.builder()
                .user(userProxy)
                .build();

        accountRepository.save(account);
    }

    @Override
    public Account topUpAccount(TopUpAccountRequestDto topUpAccountRequestDto) {
        Account account = getAccountById(UUID.fromString(topUpAccountRequestDto.getUserId()));
        account.setBalance(account.getBalance() + topUpAccountRequestDto.getAmount());
        return accountRepository.save(account);
    }

    @Override
    public void registerAnExpense(UUID userId, double amount) {
        Account account = getAccountById(userId);
        if (!haveEnoughMoney(account.getBalance(), amount)) {
            throw new NotEnoughMoneyException(userId.toString());
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    private boolean haveEnoughMoney(double balance, double amount) {
        return balance >= amount;
    }
}
