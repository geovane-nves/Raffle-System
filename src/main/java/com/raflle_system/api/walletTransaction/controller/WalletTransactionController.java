package com.raflle_system.api.walletTransaction.controller;

import com.raflle_system.api.walletTransaction.dtos.WalletTransactionResponseDTO;
import com.raflle_system.api.walletTransaction.services.WalletTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wallet-transactions")
public class WalletTransactionController {

    @Autowired
    private WalletTransactionService service;

    @GetMapping
    public List<WalletTransactionResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public WalletTransactionResponseDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/wallet/{walletId}")
    public List<WalletTransactionResponseDTO> findByWallet(
            @PathVariable UUID walletId) {

        return service.findByWallet(walletId);
    }
}