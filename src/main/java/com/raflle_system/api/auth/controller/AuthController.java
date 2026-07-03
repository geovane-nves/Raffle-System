package com.raflle_system.api.auth.controller;

import com.raflle_system.api.auth.dtos.AuthResponseDTO;
import com.raflle_system.api.auth.dtos.LoginDTO;
import com.raflle_system.api.auth.dtos.RegisterDTO;
import com.raflle_system.api.auth.dtos.RegisterResponseDTO;
import com.raflle_system.api.security.services.JwtService;
import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(dto));
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginDTO dto) {
        User user = userService.findByEmail(dto.email());
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}