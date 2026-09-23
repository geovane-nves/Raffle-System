package com.raflle_system.api.user.dtos;

import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.role.UserRole;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDTO(

        UUID id,
        String name,
        String email,
        String cpf,
        String phone,
        Instant createdAt,
        UserRole role

) {

    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getPhone(),
                user.getCreatedAt(),
                user.getRole()
        );
    }
}