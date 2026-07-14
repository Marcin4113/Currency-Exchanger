package com.marcin.currencyexchanger.role.exception;

import com.marcin.currencyexchanger.role.RoleEnum;

public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(RoleEnum role) {
        super("Role '" + role + "' already exists.");
    }
}
