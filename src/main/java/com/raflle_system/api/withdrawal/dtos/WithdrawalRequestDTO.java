package com.raflle_system.api.withdrawal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WithdrawalRequestDTO(

        @NotNull
        @Positive
        BigDecimal amount,

        @NotBlank
        String pixKey

) { }