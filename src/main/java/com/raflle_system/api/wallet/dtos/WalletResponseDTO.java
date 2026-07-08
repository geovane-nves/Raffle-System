package com.raflle_system.api.wallet.dtos;

import com.raflle_system.api.wallet.entities.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletResponseDTO(

        UUID id,
        UUID userId,
        BigDecimal availableBalance,
        BigDecimal lockedBalance

) {

    public static WalletResponseDTO fromEntity(Wallet wallet){

        return new WalletResponseDTO(

                wallet.getId(),
                wallet.getUser().getId(),
                wallet.getAvailableBalance(),
                wallet.getLockedBalance()
        );
    }
}