package com.example.expense_tracking_service.web;

import com.example.expense_tracking_service.service.exception.NotEnoughMoneyException;
import com.example.expense_tracking_service.service.exception.ResourceNotFoundException;
import com.example.expense_tracking_service.service.exception.UsernameAlreadyExistsException;
import com.example.expense_tracking_service.util.ProblemDetailBuilder;
import com.example.expense_tracking_service.web.exception.NoRequestParamsException;
import com.example.expense_tracking_service.web.exception.ParamsValidationDetails;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.BAD_REQUEST)
                .type("urn:problem-type:validation-error")
                .title("Validation Error")
                .detail("Validation Failed Exception")
                .property("validationErrors", validationDetails)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(NoRequestParamsException.class)
    public ResponseEntity<Object> handleNoRequestParams(NoRequestParamsException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.BAD_REQUEST)
                .type("urn:problem-type:no-request-params")
                .title("No Request Params Exception")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.NOT_FOUND)
                .type("urn:problem-type:resource-not-found")
                .title("Resource Not Found Exception")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<Object> handleNotEnoughMoney(NotEnoughMoneyException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.BAD_REQUEST)
                .type("urn:problem-type:not-enough-money")
                .title("Not Enough Money Exception")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.CONFLICT)
                .type("urn:problem-type:conflict-error")
                .title("Conflict Error")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwt(ExpiredJwtException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .type("urn:problem-type:expired-jwt-exception")
                .title("Expired JWT Exception")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.FORBIDDEN)
                .type("urn:problem-type:access-denied")
                .title("Access Denied Exception")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .type("urn:problem-type:unauthorized")
                .title("Authentication Exception")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleInternalServerError(RuntimeException ex) {
        ProblemDetail problemDetail = ProblemDetailBuilder.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .type("urn:problem-type:internal-server-error")
                .title("Internal Server Error")
                .detail(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(problemDetail);
    }
}
