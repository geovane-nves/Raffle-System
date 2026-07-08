package com.raflle_system.api.draw.enums;

public enum DrawStatus {

    SCHEDULED("scheduled"),
    RUNNING("running"),
    FINISHED("finished");

    private final String status;

    DrawStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}