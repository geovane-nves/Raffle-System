package com.raflle_system.api.payment.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raflle_system.api.payment.enums.PaymentStatus;
import com.raflle_system.api.paymentTransaction.entities.PaymentTransaction;
import com.raflle_system.api.purchase.entities.Purchase;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @UuidGenerator
    @Column(name = "payment_id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false, unique = true)
    private Purchase purchase;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private PaymentTransaction paymentTransaction;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(columnDefinition = "TEXT")
    private String qrCode;

    @Column(columnDefinition = "TEXT")
    private String pixCopyPaste;

    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime createdAt;

    private LocalDateTime paidAt;

    public Payment() {
        this.createdAt = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPixCopyPaste() {
        return pixCopyPaste;
    }

    public void setPixCopyPaste(String pixCopyPaste) {
        this.pixCopyPaste = pixCopyPaste;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }
}