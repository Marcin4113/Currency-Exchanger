package com.marcin.currencyexchanger.currency;

import java.math.BigDecimal;

public record Currency (
        String currency,
        String code,
        BigDecimal mid
) {}
