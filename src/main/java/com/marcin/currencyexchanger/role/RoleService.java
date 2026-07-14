package com.marcin.currencyexchanger.role;

import com.marcin.currencyexchanger.role.exception.RoleAlreadyExistsException;
import com.marcin.currencyexchanger.role.exception.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(RoleEnum name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException("Role " + name + " not found"));
    }

    public boolean existsByName(RoleEnum name) {
        return roleRepository.existsByName(name);
    }

    private void saveRoles() {
        this.saveRole(RoleEnum.ROLE_ADMIN);
        this.saveRole(RoleEnum.ROLE_USER);
    }

    private void saveRole(RoleEnum role) {
        if (!this.existsByName(role)) {
            roleRepository.save(new Role(role));
        }
        else {
            throw new RoleAlreadyExistsException(role);
        }
    }
}
