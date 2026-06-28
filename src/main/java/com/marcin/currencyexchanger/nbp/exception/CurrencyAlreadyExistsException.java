package com.marcin.currencyexchanger.nbp.exception;

public class CurrencyAlreadyExistsException extends NbpException {
    public CurrencyAlreadyExistsException(String message) {
        super(message);
    }
}
