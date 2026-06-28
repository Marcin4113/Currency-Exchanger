package com.marcin.currencyexchanger.exchange.dto;

import java.math.BigDecimal;

public record ExchangeResponse(
        BigDecimal sourceAmount,
        BigDecimal exchangedValue,
        String currencyFrom,
        String currencyTo
) {}
