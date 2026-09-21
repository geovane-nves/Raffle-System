package com.raflle_system.api.purchase.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PurchaseRequestDTO(

        @NotNull(message = "Raffle id is required")
        UUID raffleId,

        @NotEmpty(message = "At least one ticket must be selected")
        List<@NotNull(message = "Ticket id cannot be null") UUID> ticketIds

){ }