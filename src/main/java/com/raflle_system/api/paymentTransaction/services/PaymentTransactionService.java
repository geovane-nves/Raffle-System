package com.raflle_system.api.paymentTransaction.services;

import com.raflle_system.api.paymentTransaction.dtos.PaymentTransactionResponseDTO;
import com.raflle_system.api.paymentTransaction.entities.PaymentTransaction;
import com.raflle_system.api.paymentTransaction.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    public PaymentTransactionResponseDTO findByPayment(UUID paymentId){

        PaymentTransaction transaction = paymentTransactionRepository
                .findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment transaction not found."));

        return PaymentTransactionResponseDTO.fromEntity(transaction);

    }
}