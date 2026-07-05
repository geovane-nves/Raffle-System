package com.raflle_system.api.purchase.repositories;

import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.purchase.enums.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    List<Purchase> findByUserId(UUID userId);
    List<Purchase> findByRaffleId(UUID raffleId);
    List<Purchase> findByStatus(PurchaseStatus status);
}