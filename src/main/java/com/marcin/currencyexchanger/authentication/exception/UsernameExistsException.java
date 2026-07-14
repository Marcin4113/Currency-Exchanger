package com.marcin.currencyexchanger.authentication.exception;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String username) {
        super("Username " + username + " already exists.");
    }
}
