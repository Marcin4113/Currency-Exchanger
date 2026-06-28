package com.marcin.currencyexchanger.nbp.dto;

import java.util.List;

public record NbpTableResponseDto (
    String table,
    String no,
    String effectiveDate,
    List<NbpRateDto> rates
) {}
