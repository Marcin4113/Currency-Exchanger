package com.marcin.currencyexchanger.exchange;

import com.marcin.currencyexchanger.currency.Currency;
import com.marcin.currencyexchanger.exchange.exception.SameCurrenciesGivenException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ExchangeCalculator {
    private static final int SCALE = 2;

    public BigDecimal exchangeAmount(BigDecimal value, Currency fromCurrency, Currency toCurrency) {
        if (this.isSameCurrenciesGiven(fromCurrency, toCurrency)) {
            throw new SameCurrenciesGivenException(fromCurrency.code());
        }

        BigDecimal amountInPln = value.multiply(fromCurrency.mid());

        return amountInPln.divide(toCurrency.mid(), SCALE, RoundingMode.HALF_UP);
    }

    private boolean isSameCurrenciesGiven(Currency fromCurrency, Currency toCurrency) {
        return fromCurrency.code().equals(toCurrency.code());
    }
}
