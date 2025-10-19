package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.service.exception.CategoryNotFoundException;
import com.example.expense_tracking_service.service.exception.ResourceNotFoundException;
import com.example.expense_tracking_service.util.RequestValidationUtil;
import com.example.expense_tracking_service.web.exception.NoRequestParamsException;
import com.example.expense_tracking_service.web.exception.ParamsValidationDetails;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<ParamsValidationDetails> validationDetails = ex.getFieldErrors().stream()
                .map(fieldError -> ParamsValidationDetails.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build()
                )
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(RequestValidationUtil.getProblemDetailByValidationDetails(validationDetails));
    }

    @ExceptionHandler(NoRequestParamsException.class)
    public ResponseEntity<Object> handleNoRequestParams(NoRequestParamsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:no-request-params"));
        problemDetail.setTitle("No Request Params Exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("urn:problem-type:resource-not-found"));
        problemDetail.setTitle("Resource Not Found Exception");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }
}
