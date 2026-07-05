package com.raflle_system.api.user.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raflle_system.api.purchase.entities.Purchase;
import com.raflle_system.api.raffle.entities.Raffle;
import com.raflle_system.api.user.role.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User{

    @Id
    @UuidGenerator
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 100)
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Raffle> raffles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases = new ArrayList<>();

    public User(){
    }

    public User(String name, String email, String encodedPassword, UserRole role) {
        this.role = UserRole.USER;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = UserRole.USER;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
