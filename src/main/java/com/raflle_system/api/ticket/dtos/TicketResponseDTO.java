package com.raflle_system.api.ticket.dtos;

import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;

import java.time.Instant;
import java.util.UUID;

public record TicketResponseDTO(
        UUID id,
        Integer number,
        TicketStatus status,
        Instant createdAt
) {

    public static TicketResponseDTO fromEntity(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getNumber(),
                ticket.getStatus(),
                ticket.getCreatedAt()
        );
    }
}