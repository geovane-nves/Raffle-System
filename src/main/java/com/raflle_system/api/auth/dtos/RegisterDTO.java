package com.raflle_system.api.auth.dtos;

import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.role.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "CPF is required")
        @Size(max = 14, message = "Invalid CPF")
        String cpf,

        @NotBlank(message = "Number phone is required")
        String phone
) {
    public User toEntity(String encodedPassword) {
        return new User(name, email, encodedPassword, cpf, phone);
    }
}