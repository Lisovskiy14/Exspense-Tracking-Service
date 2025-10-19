package com.example.expense_tracking_service.web.exception;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ParamsValidationDetails {
    String field;
    String message;
}
