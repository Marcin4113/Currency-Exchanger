package com.marcin.currencyexchanger.exchange.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ExchangeRequest(
        @Positive(message = "Value to exchange must be grater than 0")
        @NotNull(message = "Value to exchange cannot be empty")
        BigDecimal value,

        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must consist of exactly 3 uppercase letters")
        @NotNull(message = "Source currency cannot be empty")
        String currencyFrom,

        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must consist of exactly 3 uppercase letters")
        @NotNull(message = "Target currency cannot be empty")
        String currencyTo
) {}
