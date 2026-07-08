package com.raflle_system.api.ticket.repositories;

import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.ticket.enums.TicketStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT t
    FROM Ticket t
    WHERE t.raffle.id = :raffleId
    AND t.status = 'AVAILABLE'
    ORDER BY t.number
    """)
    List<Ticket> findAvailableTickets(UUID raffleId, Pageable pageable);

    List<Ticket> findByRaffleIdAndStatus(UUID raffleId, TicketStatus status);
}