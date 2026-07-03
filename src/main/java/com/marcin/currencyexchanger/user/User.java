package com.marcin.currencyexchanger.user;

import com.marcin.currencyexchanger.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @NotNull(message = "Role is required")
    private Role role;
}
