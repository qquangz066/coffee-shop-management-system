package com.coffeeshop.admin.config;

import lombok.Getter;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return super.handleExceptionInternal(ex,
                Objects.requireNonNullElseGet(body,
                        () -> new ApiError(ex.getMessage(), ex.getClass().getSimpleName())), headers, status, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Set<String>> errorsMap = fieldErrors.stream().collect(
                Collectors.groupingBy(
                        FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));
        return new ResponseEntity<>(errorsMap.isEmpty() ? ex : errorsMap, headers, status);
    }

    @Bean
    public ErrorAttributes getErrorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
                errorAttributes.remove("trace");
                return errorAttributes;
            }
        };
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiError("Entity doesn't exist", ex.getClass().getSimpleName()), HttpStatus.NOT_FOUND);
    }

    @Getter
    static class ApiError {
        private final String message;
        private final String error;
        private final Date timestamp;

        ApiError(String message, String error) {
            this.message = message;
            this.error = error;
            timestamp = new Date();
        }
    }
}