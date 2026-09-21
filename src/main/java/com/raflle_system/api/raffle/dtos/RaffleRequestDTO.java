package com.raflle_system.api.raffle.dtos;

import com.raflle_system.api.raffle.entities.Raffle;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RaffleRequestDTO(

        @NotBlank(message = "Title is required")
        @Column(nullable = false, length = 120)
        String title,

        @NotBlank(message = "Description is required")
        @Column(nullable = false, length = 400)
        String description,

        @NotNull(message = "Ticket price is required")
        @Positive(message = "Ticket price must be greater than zero")
        BigDecimal ticketPrice,

        @NotNull(message = "Total tickets is required")
        @Positive(message = "Total ticket must be greater than zero")
        Integer totalTickets,

        @NotNull(message = "Draw date is required")
        @Future(message = "Draw date must be in the future")
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
