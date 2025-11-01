package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.domain.Account;
import com.example.expense_tracking_service.dto.account.AccountDto;
import com.example.expense_tracking_service.dto.account.TopUpAccountRequestDto;
import com.example.expense_tracking_service.service.AccountService;
import com.example.expense_tracking_service.web.mapper.AccountMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping
    public ResponseEntity<Object> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountService.getAllAccounts().stream()
                        .map(accountMapper::toAccountDto)
                        .toList());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccountById(@PathVariable UUID accountId) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountMapper.toAccountDto(accountService.getAccountById(accountId)));
    }

    @PutMapping
    public ResponseEntity<Object> topUpAccount(@Valid @RequestBody TopUpAccountRequestDto topUpAccountRequestDto) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountMapper.toAccountDto(accountService.topUpAccount(topUpAccountRequestDto)));
    }


}
