package com.raflle_system.api.user.services;

import com.raflle_system.api.auth.dtos.RegisterDTO;
import com.raflle_system.api.auth.dtos.RegisterResponseDTO;
import com.raflle_system.api.user.dtos.UserUpdateDTO;
import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.user.repositories.UserRepository;
import com.raflle_system.api.wallet.entities.Wallet;
import com.raflle_system.api.wallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WalletRepository walletRepository;

    public RegisterResponseDTO register(RegisterDTO dto) {
        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        try {
            String encodedPassword = passwordEncoder.encode(dto.password());
            User user = dto.toEntity(encodedPassword);
            User savedUser = repository.save(user);

            Wallet wallet = new Wallet();
            wallet.setUser(savedUser);
            walletRepository.save(wallet);

            return RegisterResponseDTO.fromEntity(savedUser);

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(UUID id, UserUpdateDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setName(dto.name());
        return repository.save(user);
    }

    public void delete(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repository.delete(user);
    }

    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof User user) {return user;}
        throw new RuntimeException("User not authenticated");
    }

}
