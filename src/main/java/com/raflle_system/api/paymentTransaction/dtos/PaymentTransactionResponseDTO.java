package com.raflle_system.api.paymentTransaction.dtos;

import com.raflle_system.api.paymentTransaction.entities.PaymentTransaction;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentTransactionResponseDTO(

        UUID id,
        UUID paymentId,
        String provider,
        String providerTransactionId,
        String providerStatus,
        LocalDateTime createdAt

) {

    public static PaymentTransactionResponseDTO fromEntity(PaymentTransaction transaction){

        return new PaymentTransactionResponseDTO(

                transaction.getId(),
                transaction.getPayment().getId(),
                transaction.getProvider(),
                transaction.getProviderTransactionId(),
                transaction.getProviderStatus(),
                transaction.getCreatedAt()

        );
    }
}