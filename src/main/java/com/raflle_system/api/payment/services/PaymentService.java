package com.raflle_system.api.payment.services;

import com.raflle_system.api.payment.dtos.PaymentResponseDTO;
import com.raflle_system.api.payment.entities.Payment;
import com.raflle_system.api.payment.enums.PaymentStatus;
import com.raflle_system.api.payment.repositories.PaymentRepository;
import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.purchase.enums.PurchaseStatus;
import com.raflle_system.api.purchase.repositories.PurchaseRepository;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import com.raflle_system.api.ticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public PaymentResponseDTO findByPurchase(UUID purchaseId){

        Payment payment = paymentRepository.findByPurchaseId(purchaseId)
                .orElseThrow(() -> new RuntimeException("Payment not found."));

        return PaymentResponseDTO.fromEntity(payment);
    }

    public PaymentResponseDTO confirm(UUID paymentId){

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found."));

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaidAt(LocalDateTime.now());

        Purchase purchase = payment.getPurchase();

        purchase.setStatus(PurchaseStatus.PAID);

        for(Ticket ticket : purchase.getTickets()){
            ticket.setStatus(TicketStatus.PAID);
        }

        purchaseRepository.save(purchase);
        ticketRepository.saveAll(purchase.getTickets());

        paymentRepository.save(payment);

        return PaymentResponseDTO.fromEntity(payment);

    }

}