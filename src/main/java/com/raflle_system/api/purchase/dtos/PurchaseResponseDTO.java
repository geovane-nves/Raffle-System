package com.raflle_system.api.purchase.dtos;

import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.purchase.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PurchaseResponseDTO(

        UUID id,
        UUID userId,
        UUID raffleId,
        UUID paymentId,
        List<UUID> ticketIds,
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
                purchase.getPayment() != null ? purchase.getPayment().getId() : null,
                purchase.getTickets()
                        .stream()
                        .map(ticket -> ticket.getId())
                        .toList(),
                purchase.getTotalAmount(),
                purchase.getStatus(),
                purchase.getCreatedAt(),
                purchase.getConfirmedAt()
        );
    }
}