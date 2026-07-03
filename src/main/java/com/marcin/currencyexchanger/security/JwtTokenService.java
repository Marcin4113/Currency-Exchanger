package com.marcin.currencyexchanger.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenService {
    private final String AUTHENTICATION_HEADER = "Authorization";

    private final String AUTHENTICATION_TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expiration}")
    private long jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        log.info("Generating token for user {}", userDetails.getUsername());

        Map<String, Object> claims = new HashMap<>();

        claims.put (
                "roles",
                userDetails
                        .getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        log.info("Extracting username from token: {}", token);
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        log.info("Validating token: {}", token);
        try {
            Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token);
            log.info("Token is valid: {}", token);
            return true;
        }
        catch (Exception e) {
            log.warn("Invalid token: {}", token);
            return false;
        }
    }

    public String parseJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHENTICATION_HEADER);

        if (authHeader != null && authHeader.startsWith(AUTHENTICATION_TOKEN_PREFIX)) {
            return authHeader.substring(AUTHENTICATION_TOKEN_PREFIX.length());
        }

        return null;
    }

}
