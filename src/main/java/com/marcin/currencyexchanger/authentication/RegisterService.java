package com.marcin.currencyexchanger.authentication;

import com.marcin.currencyexchanger.authentication.dto.RegisterRequestDTO;
import com.marcin.currencyexchanger.authentication.exception.UsernameExistsException;
import com.marcin.currencyexchanger.user.User;
import com.marcin.currencyexchanger.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public void validateEntry(RegisterRequestDTO registerRequestDTO) {
        if (userService.existsByUsername(registerRequestDTO.username())) {
            throw new UsernameExistsException(registerRequestDTO.username());
        }
    }

    public User registerUser(RegisterRequestDTO registerRequestDTO) {
        this.validateEntry(registerRequestDTO);

        return userService.createUser(registerRequestDTO, passwordEncoder.encode(registerRequestDTO.password()));
    }

}
