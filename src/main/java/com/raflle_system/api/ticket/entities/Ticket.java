package com.raflle_system.api.ticket.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.ticket.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @UuidGenerator
    @Column(name = "ticket_id", nullable = false)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.AVAILABLE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffle_id", nullable = false)
    private Raffle raffle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt = Instant.now();

    public Ticket() {
    }

    public Ticket(Integer number, TicketStatus status) {
        this.number = number;
        this.status = status;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Raffle getRaffle() {
        return raffle;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
