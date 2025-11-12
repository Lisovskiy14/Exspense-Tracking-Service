package com.example.expense_tracking_service.service;

import com.example.expense_tracking_service.dto.auth.SignInRequestDto;
import com.example.expense_tracking_service.dto.auth.SignUpRequestDto;

public interface AuthService {
    String signUp(SignUpRequestDto signUpRequestDto);
    String signIn(SignInRequestDto signInRequestDto);
}
