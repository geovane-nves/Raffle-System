package com.raflle_system.api.purchase.services;

import com.raflle_system.api.purchase.dtos.PurchaseRequestDTO;
import com.raflle_system.api.purchase.dtos.PurchaseResponseDTO;
import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.purchase.repositories.PurchaseRepository;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.raffle.repositories.RaffleRepository;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import com.raflle_system.api.ticket.repositories.TicketRepository;
import com.raflle_system.api.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private RaffleRepository raffleRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public PurchaseResponseDTO create(PurchaseRequestDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Raffle raffle = raffleRepository.findById(dto.raffleId())
                .orElseThrow(() -> new RuntimeException("Raffle not found."));

        List<Ticket> tickets = ticketRepository.findAllById(dto.ticketIds());

        if (tickets.size() != dto.ticketIds().size()) {
            throw new RuntimeException("One or more tickets were not found.");
        }

        for (Ticket ticket : tickets) {

            if (!ticket.getRaffle().getId().equals(raffle.getId())) {
                throw new RuntimeException("Ticket does not belong to this raffle.");
            }

            if (ticket.getStatus() != TicketStatus.AVAILABLE) {
                throw new RuntimeException("Ticket " + ticket.getNumber() + " is unavailable.");
            }
        }

        Purchase purchase = new Purchase();

        purchase.setUser(user);
        purchase.setRaffle(raffle);

        BigDecimal total = raffle.getTicketPrice()
                .multiply(BigDecimal.valueOf(tickets.size()));

        purchase.setTotalAmount(total);

        for (Ticket ticket : tickets) {
            ticket.setStatus(TicketStatus.RESERVED);
            purchase.addTicket(ticket);
        }

        Purchase savedPurchase = purchaseRepository.save(purchase);

        return PurchaseResponseDTO.fromEntity(savedPurchase);
    }

    public PurchaseResponseDTO findById(UUID id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found."));

        return PurchaseResponseDTO.fromEntity(purchase);
    }

    public List<PurchaseResponseDTO> findAll() {

        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseResponseDTO::fromEntity)
                .toList();
    }

    public void delete(UUID id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found."));

        purchaseRepository.delete(purchase);
    }
}