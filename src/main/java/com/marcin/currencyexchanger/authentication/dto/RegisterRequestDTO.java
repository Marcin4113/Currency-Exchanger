package com.marcin.currencyexchanger.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO (
        @NotBlank(message = "Username is required")
        @Size(min = 3, message = "Username must be at least 3 characters long")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {
}
