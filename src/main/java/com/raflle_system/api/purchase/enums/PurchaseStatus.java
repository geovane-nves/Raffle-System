package com.raflle_system.api.purchase.enums;

public enum PurchaseStatus {
    PENDING("pending"),
    WAITING_PAYMENT("waiting payment"),
    PAID("paid"),
    CANCELLED("cancelled"),
    EXPIRED("expired");

    private String status;

    PurchaseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}