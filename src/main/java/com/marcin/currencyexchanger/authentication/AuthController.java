package com.marcin.currencyexchanger.authentication;

import com.marcin.currencyexchanger.authentication.dto.LoginRequestDTO;
import com.marcin.currencyexchanger.authentication.dto.LoginResponseDTO;
import com.marcin.currencyexchanger.authentication.dto.RegisterRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final LoginService loginService;

    private final RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(loginService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        registerService.registerUser(registerRequestDTO);

        return ResponseEntity.ok().build();
    }
}
