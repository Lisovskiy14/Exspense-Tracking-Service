package com.example.expense_tracking_service.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {
    private UUID id;
    private String name;
}
