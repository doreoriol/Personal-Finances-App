package com.example.demo.exception;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<ApiErrorResponse.FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .sorted(Comparator.comparing(FieldError::getField))
                .map(error -> new ApiErrorResponse.FieldError(error.getField(), error.getDefaultMessage()))
                .toList();

        ApiErrorResponse body = buildError(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Validation failed",
                request.getRequestURI());
        body.setFieldErrors(fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {
        List<ApiErrorResponse.FieldError> fieldErrors = ex.getConstraintViolations()
                .stream()
                .sorted(Comparator.comparing(violation -> violation.getPropertyPath().toString()))
                .map(violation -> new ApiErrorResponse.FieldError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .toList();

        ApiErrorResponse body = buildError(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Validation failed",
                request.getRequestURI());
        body.setFieldErrors(fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleUnreadableMessage(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        ApiErrorResponse body = buildError(
                HttpStatus.BAD_REQUEST,
                "INVALID_REQUEST_BODY",
                "Request body is missing or malformed",
                request.getRequestURI());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatus(
            ResponseStatusException ex,
            HttpServletRequest request) {
        HttpStatusCode statusCode = ex.getStatusCode();
        HttpStatus status = HttpStatus.valueOf(statusCode.value());

        ApiErrorResponse body = buildError(
                status,
                toErrorCode(status),
                ex.getReason() != null ? ex.getReason() : status.getReasonPhrase(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpected(
            Exception ex,
            HttpServletRequest request) {
        ApiErrorResponse body = buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "Unexpected error",
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private ApiErrorResponse buildError(HttpStatus status, String code, String message, String path) {
        ApiErrorResponse body = new ApiErrorResponse();
        body.setTimestamp(Instant.now());
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setCode(code);
        body.setMessage(message);
        body.setPath(path);
        body.setFieldErrors(List.of());
        return body;
    }

    private String toErrorCode(HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> "BAD_REQUEST";
            case UNAUTHORIZED -> "UNAUTHORIZED";
            case FORBIDDEN -> "FORBIDDEN";
            case NOT_FOUND -> "NOT_FOUND";
            case CONFLICT -> "CONFLICT";
            default -> "REQUEST_FAILED";
        };
    }
}
