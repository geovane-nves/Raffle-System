package com.raflle_system.api.payment.controller;

import com.raflle_system.api.payment.dtos.PaymentResponseDTO;
import com.raflle_system.api.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/purchase/{purchaseId}")
    public PaymentResponseDTO findByPurchase(@PathVariable UUID purchaseId){
        return paymentService.findByPurchase(purchaseId);
    }

    @PatchMapping("/{paymentId}/confirm")
    public PaymentResponseDTO confirm(@PathVariable UUID paymentId){
        return paymentService.confirm(paymentId);
    }

}