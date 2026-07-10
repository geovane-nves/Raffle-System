package com.raflle_system.api.walletTransaction.services;

import com.raflle_system.api.walletTransaction.dtos.WalletTransactionResponseDTO;
import com.raflle_system.api.walletTransaction.entities.WalletTransaction;
import com.raflle_system.api.walletTransaction.repositories.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletTransactionService {

    @Autowired
    private WalletTransactionRepository repository;

    public WalletTransactionResponseDTO findById(UUID id) {

        WalletTransaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));

        return WalletTransactionResponseDTO.fromEntity(transaction);
    }

    public List<WalletTransactionResponseDTO> findByWallet(UUID walletId) {

        return repository.findByWalletId(walletId)
                .stream()
                .map(WalletTransactionResponseDTO::fromEntity)
                .toList();
    }

    public List<WalletTransactionResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(WalletTransactionResponseDTO::fromEntity)
                .toList();
    }
}