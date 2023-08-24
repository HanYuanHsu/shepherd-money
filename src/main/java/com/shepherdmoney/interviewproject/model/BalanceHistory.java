package com.shepherdmoney.interviewproject.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BalanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NonNull
    private Instant date;

    @NonNull
    private double balance;
    public BalanceHistory() {
        this.balance = 0;
        this.date = Instant.now();
    }

    public BalanceHistory(double balance) {
        this.balance = balance;
        this.date = Instant.now();
    }

    // adds money to current balance
    public void addMoney(double money) {
        balance += money;
    }
}
