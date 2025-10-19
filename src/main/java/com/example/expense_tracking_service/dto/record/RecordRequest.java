package com.example.expense_tracking_service.dto.record;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class RecordRequest {

    @NotBlank(message = "is required")
    String userId;

    @NotBlank(message = "is required")
    String categoryId;

    @NotBlank(message = "is required")
    double costAmount;
}
