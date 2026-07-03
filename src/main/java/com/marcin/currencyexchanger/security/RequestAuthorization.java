package com.marcin.currencyexchanger.security;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Component
public class RequestAuthorization {
    private final List<String> PUBLIC_PATHS = List.of(
            "/",
            "/auth/login"
//            "/auth/register",
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            "/swagger-ui.html"
    );

    private final AntPathMatcher matcher = new AntPathMatcher();

    public Boolean isPublicRequest(String servletPath) {
        if (servletPath == null) {
            return false;
        }

        return PUBLIC_PATHS.stream()
                .anyMatch(path -> matcher.match(path, servletPath));
    }

    public String[] getPublicPaths() {
        return PUBLIC_PATHS.toArray(new String[0]);
    }
}
