package com.raflle_system.api.draw.dtos;

import com.raflle_system.api.draw.entities.Draw;

import java.time.LocalDateTime;
import java.util.UUID;

public record DrawResponseDTO(

        UUID id,
        UUID raffleId,
        UUID winningTicketId,
        Integer winningNumber,
        String status,
        LocalDateTime executionDate

) {

    public static DrawResponseDTO fromEntity(Draw draw){

        return new DrawResponseDTO(

                draw.getId(),
                draw.getRaffle().getId(),
                draw.getWinningTicket().getId(),
                draw.getWinningTicket().getNumber(),
                draw.getStatus().name(),
                draw.getExecutionDate()
        );
    }
}