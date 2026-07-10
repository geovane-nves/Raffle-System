package com.raflle_system.api.wallet.entities;

import com.raflle_system.api.user.entities.User;
import com.raflle_system.api.walletTransaction.entities.WalletTransaction;
import com.raflle_system.api.withdrawal.entities.Withdrawal;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @UuidGenerator
    @Column(name = "wallet_id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<WalletTransaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Withdrawal> withdrawals = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal availableBalance = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal lockedBalance = BigDecimal.ZERO;

    public Wallet() {
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public BigDecimal getLockedBalance() {
        return lockedBalance;
    }

    public List<WalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<Withdrawal> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<Withdrawal> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public void credit(BigDecimal amount) {
        availableBalance = availableBalance.add(amount);
    }

    public void lockFunds(BigDecimal amount) {

        if (availableBalance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance.");
        }

        availableBalance = availableBalance.subtract(amount);
        lockedBalance = lockedBalance.add(amount);
    }

    public void unlockFunds(BigDecimal amount) {

        if (lockedBalance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient locked balance.");
        }

        lockedBalance = lockedBalance.subtract(amount);
        availableBalance = availableBalance.add(amount);
    }

    public void withdraw(BigDecimal amount) {

        if (availableBalance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance.");
        }

        availableBalance = availableBalance.subtract(amount);
    }

    public void addTransaction(WalletTransaction transaction) {
        this.transactions.add(transaction);
        transaction.setWallet(this);
    }

    public void addWithdrawal(Withdrawal withdrawal){
        withdrawals.add(withdrawal);
        withdrawal.setWallet(this);
    }
}