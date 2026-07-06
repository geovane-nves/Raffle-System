package com.raflle_system.api.payment.repositories;

import com.raflle_system.api.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByPurchaseId(UUID purchaseId);
}