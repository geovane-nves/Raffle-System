package com.raflle_system.api.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @Size(max = 100, message = "Name must have at most 100 characters")
        String name,

        String phone
) {}