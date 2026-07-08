package com.raflle_system.api.draw.services;

import com.raflle_system.api.draw.dtos.DrawResponseDTO;
import com.raflle_system.api.draw.entities.Draw;
import com.raflle_system.api.draw.enums.DrawStatus;
import com.raflle_system.api.draw.repositories.DrawRepository;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.raffle.enums.RaffleStatus;
import com.raflle_system.api.raffle.repositories.RaffleRepository;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import com.raflle_system.api.ticket.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private RaffleRepository raffleRepository;

    private final SecureRandom random = new SecureRandom();

    @Transactional
    public DrawResponseDTO execute(UUID raffleId){

        Raffle raffle = raffleRepository.findById(raffleId)
                .orElseThrow(() -> new RuntimeException("Raffle not found."));

        if(raffle.getStatus() != RaffleStatus.FINISHED){
            throw new RuntimeException("Raffle is not finished.");
        }

        if(drawRepository.findByRaffleId(raffleId).isPresent()){
            throw new RuntimeException("Draw already executed.");
        }

        List<Ticket> paidTickets = ticketRepository.findByRaffleIdAndStatus(raffleId, TicketStatus.PAID);

        if(paidTickets.isEmpty()){
            throw new RuntimeException("No paid tickets.");
        }

        Draw draw = new Draw();

        draw.setRaffle(raffle);
        draw.setStatus(DrawStatus.RUNNING);

        Ticket winner = paidTickets.get(random.nextInt(paidTickets.size()));

        draw.setWinningTicket(winner);

        draw.setStatus(DrawStatus.FINISHED);

        raffle.setStatus(RaffleStatus.DRAWN);

        raffleRepository.save(raffle);

        Draw saved = drawRepository.save(draw);

        return DrawResponseDTO.fromEntity(saved);

    }

    public DrawResponseDTO findById(UUID id){

        return DrawResponseDTO.fromEntity(
                drawRepository.findById(id).orElseThrow(() -> new RuntimeException("Draw not found."))
        );

    }

}