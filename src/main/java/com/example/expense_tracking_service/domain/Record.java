package com.example.expense_tracking_service.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Record {
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private LocalDateTime date;
    private double costAmount;
}
