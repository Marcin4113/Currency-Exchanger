package com.marcin.currencyexchanger.authentication.dto;

public record LoginResponseDTO(
        String username,
        String jwtToken
) {
}
