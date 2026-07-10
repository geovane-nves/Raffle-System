package com.raflle_system.api.withdrawal.repositories;

import com.raflle_system.api.withdrawal.entities.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, UUID> {
    List<Withdrawal> findByWalletId(UUID walletId);
}