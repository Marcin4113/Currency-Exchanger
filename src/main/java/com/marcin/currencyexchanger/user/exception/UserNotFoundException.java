package com.marcin.currencyexchanger.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Username not found: " + username);
    }
}
