    package com.raflle_system.api.wallet.controller;

import com.raflle_system.api.wallet.dtos.WalletResponseDTO;
import com.raflle_system.api.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/me")
    public WalletResponseDTO myWallet() {
        return walletService.findMyWallet();
    }

    @GetMapping("/{id}")
    public WalletResponseDTO findById(@PathVariable UUID id) {
        return walletService.findById(id);
    }

    @GetMapping
    public List<WalletResponseDTO> findAll() {
        return walletService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        walletService.delete(id);
    }
}