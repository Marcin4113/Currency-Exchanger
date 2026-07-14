package com.marcin.currencyexchanger.user;

import com.marcin.currencyexchanger.authentication.dto.RegisterRequestDTO;
import com.marcin.currencyexchanger.role.Role;
import com.marcin.currencyexchanger.role.RoleEnum;
import com.marcin.currencyexchanger.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User createUser(RegisterRequestDTO registerRequestDTO, String encryptedPassword) {
        User user = userMapper.toUser(registerRequestDTO, encryptedPassword);

        Role userRole = roleService.findByName(RoleEnum.ROLE_USER);
        user.setRole(userRole);

        return userRepository.save(user);
    }
}
