package com.raflle_system.api.walletTransaction.enums;

public enum TransactionType {

    LOCKED("locked"),
    RELEASED("released"),
    WITHDRAWAL("withdrawal"),
    REFUND("refund");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}