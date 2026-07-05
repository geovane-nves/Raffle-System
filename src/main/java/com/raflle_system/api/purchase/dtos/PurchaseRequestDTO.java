package com.raflle_system.api.purchase.dtos;

import java.util.List;
import java.util.UUID;

public record PurchaseRequestDTO(

        UUID raffleId,
        List<UUID> ticketIds

) {
}