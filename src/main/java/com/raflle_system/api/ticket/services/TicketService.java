package com.raflle_system.api.ticket.services;

import com.raflle_system.api.ticket.dtos.TicketResponseDTO;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import com.raflle_system.api.ticket.exceptions.NoAvailableTicketsException;
import com.raflle_system.api.ticket.exceptions.TicketNotFoundException;
import com.raflle_system.api.ticket.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

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

    @Transactional
    public TicketResponseDTO findById(UUID id) {

        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found."));

        return TicketResponseDTO.fromEntity(ticket);
    }

}