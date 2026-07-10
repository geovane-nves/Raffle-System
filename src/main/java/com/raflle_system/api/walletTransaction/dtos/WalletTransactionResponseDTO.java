package com.raflle_system.api.walletTransaction.dtos;

import com.raflle_system.api.walletTransaction.entities.WalletTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletTransactionResponseDTO(

        UUID id,
        UUID walletId,
        BigDecimal amount,
        String type,
        String description,
        LocalDateTime createdAt

) {

    public static WalletTransactionResponseDTO fromEntity(WalletTransaction transaction) {

        return new WalletTransactionResponseDTO(

                transaction.getId(),
                transaction.getWallet().getId(),
                transaction.getAmount(),
                transaction.getType().name(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }
}