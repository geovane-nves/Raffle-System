package com.raflle_system.api.withdrawal.controller;

import com.raflle_system.api.withdrawal.dtos.WithdrawalRequestDTO;
import com.raflle_system.api.withdrawal.dtos.WithdrawalResponseDTO;
import com.raflle_system.api.withdrawal.services.WithdrawalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalService service;

    @PostMapping
    public WithdrawalResponseDTO request(@RequestBody @Valid WithdrawalRequestDTO dto){
        return service.request(dto);
    }

    @PatchMapping("/{id}/approve")
    public WithdrawalResponseDTO approve(@PathVariable UUID id){
        return service.approve(id);
    }

    @PatchMapping("/{id}/reject")
    public WithdrawalResponseDTO reject(@PathVariable UUID id){
        return service.reject(id);
    }

    @GetMapping("/{id}")
    public WithdrawalResponseDTO findById(@PathVariable UUID id){
        return service.findById(id);
    }

    @GetMapping
    public List<WithdrawalResponseDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/wallet/{walletId}")
    public List<WithdrawalResponseDTO> findByWallet(@PathVariable UUID walletId){
        return service.findByWallet(walletId);
    }

}