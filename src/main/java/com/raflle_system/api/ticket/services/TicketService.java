package com.raflle_system.api.ticket.services;

import com.raflle_system.api.exceptions.NotFoundException;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.raffle.repositories.RaffleRepository;
import com.raflle_system.api.ticket.dtos.TicketResponseDTO;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import com.raflle_system.api.ticket.exceptions.NoAvailableTicketsException;
import com.raflle_system.api.ticket.exceptions.TicketNotFoundException;
import com.raflle_system.api.ticket.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private RaffleRepository raffleRepository;

    @Transactional
    public Ticket reserveTicket(UUID raffleId) {
        Ticket ticket = repository
                .findAvailableTickets(raffleId, PageRequest.of(0,1))
                .stream()
                .findFirst()
                .orElseThrow(NoAvailableTicketsException::new);

        ticket.setStatus(TicketStatus.RESERVED);

        return repository.save(ticket);
    }

    @Transactional
    public List<TicketResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(TicketResponseDTO::fromEntity)
                .toList();
    }

    public List<TicketResponseDTO> findByRaffle(UUID raffleId) {
        if (!raffleRepository.existsById(raffleId)) { throw new NotFoundException("Raffle not found."); }

        return repository.findByRaffleIdOrderByNumber(raffleId)
                .stream()
                .map(TicketResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public TicketResponseDTO findById(UUID id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ticket not found."));

        return TicketResponseDTO.fromEntity(ticket);
    }
}