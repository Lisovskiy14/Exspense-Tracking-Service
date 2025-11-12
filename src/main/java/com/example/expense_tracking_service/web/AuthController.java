package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.dto.auth.JwtAuthResponseDto;
import com.example.expense_tracking_service.dto.auth.SignInRequestDto;
import com.example.expense_tracking_service.dto.auth.SignUpRequestDto;
import com.example.expense_tracking_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JwtAuthResponseDto(
                        authService.signUp(signUpRequestDto)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new JwtAuthResponseDto(
                        authService.signIn(signInRequestDto)));
    }
}
