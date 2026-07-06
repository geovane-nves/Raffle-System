package com.raflle_system.api.payment.dtos;

import com.raflle_system.api.payment.entities.Payment;
import com.raflle_system.api.payment.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponseDTO(

        UUID id,
        UUID purchaseId,
        BigDecimal amount,
        PaymentStatus status,
        String qrCode,
        String pixCopyPaste,
        LocalDateTime createdAt,
        LocalDateTime paidAt
) {

    public static PaymentResponseDTO fromEntity(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId(),
                payment.getPurchase().getId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getQrCode(),
                payment.getPixCopyPaste(),
                payment.getCreatedAt(),
                payment.getPaidAt()
        );
    }

}