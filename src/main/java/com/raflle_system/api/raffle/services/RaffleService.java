package com.raflle_system.api.raffle.services;

import com.raflle_system.api.raffle.dtos.RaffleRequestDTO;
import com.raflle_system.api.raffle.dtos.RaffleResponseDTO;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.raffle.repositories.RaffleRepository;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import com.raflle_system.api.ticket.repositories.TicketRepository;
import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RaffleService {

    @Autowired
    private RaffleRepository repository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public RaffleResponseDTO create(RaffleRequestDTO dto) {
        User creator = userService.getAuthenticatedUser();
        Raffle raffle = dto.toEntity();
        raffle.setCreator(creator);
        Raffle savedRaffle = repository.save(raffle);

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 1; i <= savedRaffle.getTotalTickets(); i++) {
            Ticket ticket = new Ticket();
            ticket.setNumber(i);
            ticket.setStatus(TicketStatus.AVAILABLE);
            ticket.setRaffle(savedRaffle);

            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);

        return RaffleResponseDTO.fromEntity(savedRaffle);
    }

    public List<RaffleResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(RaffleResponseDTO::fromEntity)
                .toList();
    }

    public RaffleResponseDTO findById(UUID id) {
        Raffle raffle = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Raffle not found"));

        return RaffleResponseDTO.fromEntity(raffle);
    }

    public RaffleResponseDTO update(UUID id, RaffleRequestDTO dto) {

        Raffle raffle = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Raffle not found"));

        raffle.setTitle(dto.title());
        raffle.setDescription(dto.description());
        raffle.setTicketPrice(dto.ticketPrice());
        raffle.setTotalTickets(dto.totalTickets());
        raffle.setDrawDate(dto.drawDate());

        Raffle updated = repository.save(raffle);

        return RaffleResponseDTO.fromEntity(updated);
    }

    public void delete(UUID id) {

        Raffle raffle = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Raffle not found"));

        repository.delete(raffle);
    }


}