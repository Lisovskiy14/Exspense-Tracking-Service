package com.example.expense_tracking_service.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class CategoryRequest {

    @NotBlank(message = "is required")
    @Size(min = 3, max = 20, message = "must be between 3 and 20 characters")
    String name;
}
