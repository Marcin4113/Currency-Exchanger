package com.marcin.currencyexchanger.exchange;

import com.marcin.currencyexchanger.currency.Currency;
import com.marcin.currencyexchanger.exchange.dto.ExchangeRequest;
import com.marcin.currencyexchanger.exchange.dto.ExchangeResponse;
import com.marcin.currencyexchanger.nbp.NbpService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ExchangeService {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    private final NbpService nbpService;

    private final ExchangeCalculator exchangeCalculator;

    public ExchangeResponse exchangeCurrency(ExchangeRequest exchangeRequest) {
        logger.info("Exchange currency request: {}", exchangeRequest);

        Currency currencyFrom = nbpService.getCurrencyByCode(exchangeRequest.currencyFrom());
        Currency currencyTo = nbpService.getCurrencyByCode(exchangeRequest.currencyTo());

        BigDecimal exchangedValue = exchangeCalculator.exchangeAmount(
                exchangeRequest.value(),
                currencyFrom,
                currencyTo);

        return new ExchangeResponse(
                exchangeRequest.value(),
                exchangedValue,
                exchangeRequest.currencyFrom(),
                exchangeRequest.currencyTo()
        );
    }
}
