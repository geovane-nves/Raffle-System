package com.raflle_system.api.ticket.exceptions;

public class NoAvailableTicketsException extends RuntimeException {

    public NoAvailableTicketsException() {
        super("There are no tickets available.");
    }
}