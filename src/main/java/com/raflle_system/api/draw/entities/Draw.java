package com.raflle_system.api.draw.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raflle_system.api.draw.enums.DrawStatus;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.ticket.entities.Ticket;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "draws")
public class Draw {

    @Id
    @UuidGenerator
    @Column(name = "draw_id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffle_id", nullable = false, unique = true)
    private Raffle raffle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winning_ticket_id")
    private Ticket winningTicket;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrawStatus status;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime executionDate;

    public Draw() {
        this.executionDate = LocalDateTime.now();
        this.status = DrawStatus.SCHEDULED;
    }

    public UUID getId() {
        return id;
    }

    public Raffle getRaffle() {
        return raffle;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    public Ticket getWinningTicket() {
        return winningTicket;
    }

    public void setWinningTicket(Ticket winningTicket) {
        this.winningTicket = winningTicket;
    }

    public DrawStatus getStatus() {
        return status;
    }

    public void setStatus(DrawStatus status) {
        this.status = status;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }
}