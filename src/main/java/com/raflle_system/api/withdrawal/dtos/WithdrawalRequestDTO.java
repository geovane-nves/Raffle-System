package com.raflle_system.api.withdrawal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record WithdrawalRequestDTO(

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotBlank(message = "Pix key is required")
        @Size(max = 255, message = "Pix key must have at most 255 characters")
        String pixKey

) { }