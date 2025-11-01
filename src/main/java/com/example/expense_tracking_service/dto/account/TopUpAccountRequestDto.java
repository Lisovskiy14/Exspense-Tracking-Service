package com.example.expense_tracking_service.dto.account;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class TopUpAccountRequestDto {

    @NotBlank(message = "is required")
    String userId;

    @DecimalMin(value = "1", message = "must be at least 1")
    double amount;

}
