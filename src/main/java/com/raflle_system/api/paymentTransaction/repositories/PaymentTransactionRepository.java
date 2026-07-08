package com.raflle_system.api.paymentTransaction.repositories;

import com.raflle_system.api.paymentTransaction.entities.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, UUID> {
    Optional<PaymentTransaction> findByPaymentId(UUID paymentId);
}