package com.marcin.currencyexchanger.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * Wyszukuje użytkownika po username
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    /**
     * Waliduje kredencjały użytkownika
     */
    public boolean validateCredentials(String username, String rawPassword) {
        Optional<User> user = findByUsername(username);
        
        if (user.isEmpty()) {
            return false;
        }
        
        return passwordEncoder.matches(rawPassword, user.get().getPassword());
    }
}
