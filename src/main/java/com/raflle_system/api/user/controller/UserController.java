package com.raflle_system.api.user.controller;

import com.raflle_system.api.user.dtos.UserUpdateDTO;
import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping("/me")
    public User update(@RequestBody UserUpdateDTO dto, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        return service.update(loggedUser.getId(), dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}