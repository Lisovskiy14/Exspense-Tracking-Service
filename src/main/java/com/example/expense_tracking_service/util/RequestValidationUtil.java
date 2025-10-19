package com.example.expense_tracking_service.util;

import com.example.expense_tracking_service.web.exception.ParamsValidationDetails;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.util.List;

@UtilityClass
public class RequestValidationUtil {

    public static ProblemDetail getProblemDetailByValidationDetails(List<ParamsValidationDetails> validationDetails) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request validation failed");
        problemDetail.setType(URI.create("urn:problem-type:validation-error"));
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("validationErrors", validationDetails);
        return problemDetail;
    }
}
