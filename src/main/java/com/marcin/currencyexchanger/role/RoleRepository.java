package com.marcin.currencyexchanger.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(RoleEnum name);

    Optional<Role> findByName(RoleEnum name);
}
