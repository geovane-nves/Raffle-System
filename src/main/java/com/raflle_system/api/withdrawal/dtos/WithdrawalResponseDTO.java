package com.raflle_system.api.withdrawal.dtos;

import com.raflle_system.api.withdrawal.entities.Withdrawal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WithdrawalResponseDTO(

        UUID id,
        UUID walletId,
        BigDecimal amount,
        String pixKey,
        String status,
        LocalDateTime requestedAt,
        LocalDateTime processedAt

) {

    public static WithdrawalResponseDTO fromEntity(Withdrawal withdrawal){

        return new WithdrawalResponseDTO(

                withdrawal.getId(),
                withdrawal.getWallet().getId(),
                withdrawal.getAmount(),
                withdrawal.getPixKey(),
                withdrawal.getStatus().name(),
                withdrawal.getRequestedAt(),
                withdrawal.getProcessedAt()
        );
    }
}