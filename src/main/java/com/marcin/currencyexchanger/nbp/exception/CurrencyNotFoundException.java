package com.marcin.currencyexchanger.nbp.exception;

public class CurrencyNotFoundException extends NbpException {
    public CurrencyNotFoundException(String message) {
        super("Currency code '" + message + "' not found!");
    }
}
