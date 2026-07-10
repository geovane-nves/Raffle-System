package com.raflle_system.api.withdrawal.services;

import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.wallet.entities.Wallet;
import com.raflle_system.api.wallet.repositories.WalletRepository;
import com.raflle_system.api.walletTransaction.entities.WalletTransaction;
import com.raflle_system.api.walletTransaction.enums.TransactionType;
import com.raflle_system.api.walletTransaction.repositories.WalletTransactionRepository;
import com.raflle_system.api.withdrawal.dtos.WithdrawalRequestDTO;
import com.raflle_system.api.withdrawal.dtos.WithdrawalResponseDTO;
import com.raflle_system.api.withdrawal.entities.Withdrawal;
import com.raflle_system.api.withdrawal.enums.WithdrawalStatus;
import com.raflle_system.api.withdrawal.repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    public WithdrawalResponseDTO request(WithdrawalRequestDTO dto){

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Wallet wallet = walletRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found."));

        Withdrawal withdrawal = new Withdrawal();

        withdrawal.setAmount(dto.amount());
        withdrawal.setPixKey(dto.pixKey());

        wallet.addWithdrawal(withdrawal);

        withdrawalRepository.save(withdrawal);

        return WithdrawalResponseDTO.fromEntity(withdrawal);

    }

    public WithdrawalResponseDTO approve(UUID id){

        Withdrawal withdrawal = withdrawalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Withdrawal not found."));

        if(withdrawal.getStatus() != WithdrawalStatus.PENDING){
            throw new RuntimeException("Withdrawal already processed.");
        }

        Wallet wallet = withdrawal.getWallet();

        wallet.withdraw(withdrawal.getAmount());

        withdrawal.setStatus(WithdrawalStatus.APPROVED);
        withdrawal.setProcessedAt(LocalDateTime.now());

        WalletTransaction transaction = new WalletTransaction();

        transaction.setAmount(withdrawal.getAmount());
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setDescription("Withdrawal");

        wallet.addTransaction(transaction);

        walletRepository.save(wallet);
        walletTransactionRepository.save(transaction);
        withdrawalRepository.save(withdrawal);

        return WithdrawalResponseDTO.fromEntity(withdrawal);

    }

    public WithdrawalResponseDTO reject(UUID id){

        Withdrawal withdrawal = withdrawalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Withdrawal not found."));

        withdrawal.setStatus(WithdrawalStatus.REJECTED);
        withdrawal.setProcessedAt(LocalDateTime.now());

        withdrawalRepository.save(withdrawal);

        return WithdrawalResponseDTO.fromEntity(withdrawal);

    }

    public WithdrawalResponseDTO findById(UUID id){

        return WithdrawalResponseDTO.fromEntity(
                withdrawalRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Withdrawal not found."))
        );

    }

    public List<WithdrawalResponseDTO> findAll(){

        return withdrawalRepository.findAll()
                .stream()
                .map(WithdrawalResponseDTO::fromEntity)
                .toList();

    }

    public List<WithdrawalResponseDTO> findByWallet(UUID walletId){

        return withdrawalRepository.findByWalletId(walletId)
                .stream()
                .map(WithdrawalResponseDTO::fromEntity)
                .toList();

    }

}