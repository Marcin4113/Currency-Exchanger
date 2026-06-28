package com.marcin.currencyexchanger.exchange.exception;

public class SameCurrenciesGivenException extends ExchangeException {
    public SameCurrenciesGivenException(String currencyCode) {
        super("Same currencies given in the parameter: " + currencyCode);
    }
}
