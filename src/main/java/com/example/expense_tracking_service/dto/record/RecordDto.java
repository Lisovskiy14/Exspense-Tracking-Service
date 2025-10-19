package com.example.expense_tracking_service.dto.record;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class RecordDto {
    String id;
    String userId;
    String categoryId;
    String date;
    double costAmount;
}
