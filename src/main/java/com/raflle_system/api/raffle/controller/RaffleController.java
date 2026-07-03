package com.raflle_system.api.raffle.controller;

import com.raflle_system.api.raffle.dtos.RaffleRequestDTO;
import com.raflle_system.api.raffle.dtos.RaffleResponseDTO;
import com.raflle_system.api.raffle.services.RaffleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/raffles")
public class RaffleController {

    @Autowired
    private RaffleService service;

    @PostMapping
    public ResponseEntity<RaffleResponseDTO> create(@RequestBody @Valid RaffleRequestDTO dto) {
        RaffleResponseDTO response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RaffleResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaffleResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaffleResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid RaffleRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}