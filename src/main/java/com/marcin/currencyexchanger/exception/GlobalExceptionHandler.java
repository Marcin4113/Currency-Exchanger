package com.marcin.currencyexchanger.exception;

import com.marcin.currencyexchanger.exchange.exception.ExchangeException;
import com.marcin.currencyexchanger.nbp.exception.CurrencyNotFoundException;
import com.marcin.currencyexchanger.nbp.exception.NbpException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        Map<String, String> errors = new HashMap();

        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode(),
                ex.getStatusCode().value(),
                errors,
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponse> handleMissingPathVariableException(
            MissingPathVariableException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap();

        errors.put(ex.getVariableName(), ex.getMessage());

        return sendResponse(errors, (HttpStatus) ex.getStatusCode(), request.getRequestURI());
    }

    @ExceptionHandler(NbpException.class)
    public ResponseEntity<ErrorResponse> handleNbpException(
            NbpException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap();
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
        Map<String, String> errors = new HashMap();
        HttpStatus status = HttpStatus.NOT_FOUND;
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
