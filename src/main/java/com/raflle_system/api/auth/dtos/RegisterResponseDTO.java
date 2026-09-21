package com.raflle_system.api.auth.dtos;


import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.role.UserRole;

import java.time.Instant;
import java.util.UUID;

public record RegisterResponseDTO(
        UUID id,
        String name,
        String email,
        String cpf,
        String phone,
        UserRole role,
        Instant createdAt
) {
    public static RegisterResponseDTO fromEntity(User user){
        return new RegisterResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getPhone(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
