package com.raflle_system.api.ticket.enums;

public enum TicketStatus {
    AVAILABLE("available"),
    RESERVED("reserved"),
    PAID("paid");

    private String ticketStatus;

    TicketStatus(String ticketStatus){
        this.ticketStatus = ticketStatus;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }
}
