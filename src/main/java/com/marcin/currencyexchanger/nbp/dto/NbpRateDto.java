package com.marcin.currencyexchanger.nbp.dto;

public record NbpRateDto (
        String currency,
        String code,
        Double mid
) {}
