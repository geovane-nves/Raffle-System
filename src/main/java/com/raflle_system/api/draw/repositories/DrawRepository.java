package com.raflle_system.api.draw.repositories;

import com.raflle_system.api.draw.entities.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DrawRepository extends JpaRepository<Draw, UUID> {
    Optional<Draw> findByRaffleId(UUID raffleId);
}