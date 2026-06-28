package com.marcin.currencyexchanger.currency;

import com.marcin.currencyexchanger.nbp.dto.NbpRateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyMapper {
    Currency toCurrency(NbpRateDto nbpRateDto);
}
