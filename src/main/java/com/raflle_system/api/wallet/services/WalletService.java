package com.raflle_system.api.wallet.services;

import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.wallet.dtos.WalletResponseDTO;
import com.raflle_system.api.wallet.entities.Wallet;
import com.raflle_system.api.wallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public WalletResponseDTO findMyWallet() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found."));

        return WalletResponseDTO.fromEntity(wallet);
    }

    public WalletResponseDTO findById(UUID id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found."));

        return WalletResponseDTO.fromEntity(wallet);
    }

    public List<WalletResponseDTO> findAll() {
        return walletRepository.findAll()
                .stream()
                .map(WalletResponseDTO::fromEntity)
                .toList();
    }

    public void delete(UUID id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found."));

        walletRepository.delete(wallet);
    }
}