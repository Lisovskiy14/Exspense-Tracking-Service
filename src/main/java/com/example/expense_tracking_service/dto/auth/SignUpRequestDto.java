package com.example.expense_tracking_service.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class SignUpRequestDto {

    @NotBlank(message = "is required")
    @Size(min = 3, max = 20, message = "must be between 3 and 20 characters")
    String username;

    @NotBlank(message = "is required")
    @Size(min = 6, max = 30, message = "must be between 6 and 30 characters")
    String password;
}
