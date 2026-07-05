package com.raflle_system.api.purchase.dtos;

import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.purchase.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PurchaseResponseDTO(

        UUID id,
        UUID userId,
        UUID raffleId,
        BigDecimal totalAmount,
        PurchaseStatus status,
        LocalDateTime createdAt,
        LocalDateTime confirmedAt

) {

    public static PurchaseResponseDTO fromEntity(Purchase purchase) {

        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getUser().getId(),
                purchase.getRaffle().getId(),
                purchase.getTotalAmount(),
                purchase.getStatus(),
                purchase.getCreatedAt(),
                purchase.getConfirmedAt()
        );
    }
}