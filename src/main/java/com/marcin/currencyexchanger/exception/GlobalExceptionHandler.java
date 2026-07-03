package com.marcin.currencyexchanger.exception;

import com.marcin.currencyexchanger.exchange.exception.ExchangeException;
import com.marcin.currencyexchanger.nbp.exception.CurrencyNotFoundException;
import com.marcin.currencyexchanger.nbp.exception.NbpException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach((violation) -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));

        return sendResponse(errors, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return sendResponse(errors, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponse> handleMissingPathVariableException(
            MissingPathVariableException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        errors.put(ex.getVariableName(), ex.getMessage());

        return sendResponse(errors, (HttpStatus) ex.getStatusCode(), request.getRequestURI());
    }

    @ExceptionHandler(NbpException.class)
    public ResponseEntity<ErrorResponse> handleNbpException(
            NbpException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        errors.put("error", ex.getMessage());

        return sendResponse(errors, status, request.getRequestURI());
    }

    @ExceptionHandler(ExchangeException.class)
    public ResponseEntity<ErrorResponse> handleExchangeException(
            ExchangeException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        errors.put("error", ex.getMessage());

        return sendResponse(errors, status, request.getRequestURI());
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCurrencyNotFoundException(
            CurrencyNotFoundException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.NOT_FOUND;
        errors.put("error", ex.getMessage());

        return sendResponse(errors, status, request.getRequestURI());
    }

    @ExceptionHandler({
            BadCredentialsException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            RuntimeException exception,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        errors.put("error", exception.getMessage());

        return sendResponse(errors, status, request.getRequestURI());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationCredentialsNotFoundException(
            AuthenticationCredentialsNotFoundException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        errors.put("error", ex.getMessage());

        return sendResponse(errors, status, request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> sendResponse(Map<String, String> errors, HttpStatus status, String requestURI) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status,
                status.value(),
                errors,
                requestURI
        );

        return new ResponseEntity<>(errorResponse, status);
    }
}
