package com.example.expense_tracking_service.dto.category;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class CategoryListDto {
    List<CategoryDto> categories;
}
