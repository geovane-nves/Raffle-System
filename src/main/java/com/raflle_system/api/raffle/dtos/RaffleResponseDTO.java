package com.raflle_system.api.raffle.dtos;

import com.raflle_system.api.raffle.entities.Raffle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RaffleResponseDTO(
        UUID id,
        String title,
        String description,
        BigDecimal ticketPrice,
        Integer totalTickets,
        LocalDateTime drawDate
) {
    public static RaffleResponseDTO fromEntity(Raffle raffle) {
        return new RaffleResponseDTO(
                raffle.getId(),
                raffle.getTitle(),
                raffle.getDescription(),
                raffle.getTicketPrice(),
                raffle.getTotalTickets(),
                raffle.getDrawDate()
        );
    }
}