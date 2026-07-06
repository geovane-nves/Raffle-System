package com.raflle_system.api.payment.enums;

public enum PaymentStatus {

    PENDING("pending"),
    PAID("paid"),
    EXPIRED("expired"),
    FAILED("failed"),
    REFUNDED("refunded");

    private String paymentStatus;

    PaymentStatus(String paymentStatus){
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}