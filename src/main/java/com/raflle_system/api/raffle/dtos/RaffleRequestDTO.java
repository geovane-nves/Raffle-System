package com.raflle_system.api.raffle.dtos;

import com.raflle_system.api.raffle.entities.Raffle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RaffleRequestDTO(
        String title,
        String description,
        BigDecimal ticketPrice,
        Integer totalTickets,
        LocalDateTime drawDate
) {
    public Raffle toEntity() {
        Raffle raffle = new Raffle();

        raffle.setTitle(title);
        raffle.setDescription(description);
        raffle.setTicketPrice(ticketPrice);
        raffle.setTotalTickets(totalTickets);
        raffle.setDrawDate(drawDate);

        return raffle;
    }
}
