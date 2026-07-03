package com.raflle_system.api.ticket.controller;

import com.raflle_system.api.ticket.dtos.TicketResponseDTO;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/raffle/{raffleId}/reserve")
    public ResponseEntity<TicketResponseDTO> reserveTicket(
            @PathVariable UUID raffleId) {

        Ticket ticket = ticketService.reserveTicket(raffleId);
        return ResponseEntity.ok(TicketResponseDTO.fromEntity(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }
}