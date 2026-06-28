package com.marcin.currencyexchanger.exception;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        LocalDateTime timestamp,
        HttpStatusCode status,
        int  statusCode,
        Map<String, String> errors,
        String path
) {}
