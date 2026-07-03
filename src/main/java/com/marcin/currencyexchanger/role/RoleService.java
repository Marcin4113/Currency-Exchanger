package com.marcin.currencyexchanger.role;

import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        saveRoles();
    }

    private void saveRoles() {
        this.saveRole(RoleEnum.ADMIN);
        this.saveRole(RoleEnum.USER);
    }

    private void saveRole(RoleEnum name) {
        if (!roleRepository.existsByName(name)) {
            roleRepository.save(new Role(name));
        }
    }
}
