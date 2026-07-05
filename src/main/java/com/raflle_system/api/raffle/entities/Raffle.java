package com.raflle_system.api.raffle.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.raffle.enums.RaffleStatus;
import com.raflle_system.api.ticket.entities.Ticket;
import com.raflle_system.api.user.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "raffle")
public class Raffle {

    @Id
    @UuidGenerator
    @Column(name = "raffle_id", nullable = false)
    private UUID id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false, length = 120)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 400)
    private String description;

    @NotNull(message = "Ticket price is required")
    @Positive(message = "Ticket price must be greater than zero")
    @Column(nullable = false)
    private BigDecimal ticketPrice;

    @NotNull(message = "Total tickets is required")
    @Positive(message = "Total ticket must be greater than zero")
    @Column(nullable = false)
    private Integer totalTickets;

    @NotNull(message = "Draw date is required")
    @Future(message = "Draw date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime drawDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RaffleStatus status = RaffleStatus.OPEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "raffle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "raffle")
    private List<Purchase> purchases = new ArrayList<>();

    public Raffle() {
    }

    public Raffle(String title, String description, BigDecimal ticketPrice, Integer totalTickets, LocalDateTime drawDate) {
        this.title = title;
        this.description = description;
        this.ticketPrice = ticketPrice;
        this.totalTickets = totalTickets;
        this.drawDate = drawDate;
        this.status = RaffleStatus.OPEN;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public LocalDateTime getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }

    public RaffleStatus getStatus() {
        return status;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
