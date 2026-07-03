package com.marcin.currencyexchanger.authentication;

import com.marcin.currencyexchanger.authentication.dto.LoginRequestDTO;
import com.marcin.currencyexchanger.authentication.dto.LoginResponseDTO;
import com.marcin.currencyexchanger.security.JwtTokenService;
import com.marcin.currencyexchanger.user_details.UserDetailsImpl;
import com.marcin.currencyexchanger.user_details.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenService jwtTokenService;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.username(),
                        loginRequestDTO.password()
                )
        );

        final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequestDTO.username());

        String jwtToken = jwtTokenService.generateToken(userDetails);

        return new LoginResponseDTO(
                loginRequestDTO.username(),
                jwtToken
        );
    }
}
