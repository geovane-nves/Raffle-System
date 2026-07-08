package com.raflle_system.api.draw.controller;

import com.raflle_system.api.draw.dtos.DrawResponseDTO;
import com.raflle_system.api.draw.services.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/draws")
public class DrawController {

    @Autowired
    private DrawService drawService;

    @PostMapping("/{raffleId}")
    public DrawResponseDTO execute(@PathVariable UUID raffleId){
        return drawService.execute(raffleId);
    }

    @GetMapping("/{id}")
    public DrawResponseDTO findById(@PathVariable UUID id){
        return drawService.findById(id);
    }
}