package com.raflle_system.api.withdrawal.enums;

public enum WithdrawalStatus {

    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String status;

    WithdrawalStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}