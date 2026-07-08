package com.raflle_system.api.paymentTransaction.controller;

import com.raflle_system.api.paymentTransaction.dtos.PaymentTransactionResponseDTO;
import com.raflle_system.api.paymentTransaction.services.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payment-transactions")
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @GetMapping("/payment/{paymentId}")
    public PaymentTransactionResponseDTO findByPayment(@PathVariable UUID paymentId){

        return paymentTransactionService.findByPayment(paymentId);

    }

}