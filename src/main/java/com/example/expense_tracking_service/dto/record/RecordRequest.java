package com.example.expense_tracking_service.dto.record;

import jakarta.validation.constraints.DecimalMin;
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

    @DecimalMin(value = "1.0", message = "must be greater than or equal to 1.0")
    double costAmount;
}
