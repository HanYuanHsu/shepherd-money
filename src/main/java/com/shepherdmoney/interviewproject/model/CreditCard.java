package com.shepherdmoney.interviewproject.model;

import com.shepherdmoney.interviewproject.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Optional;
import java.util.TreeMap;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String issuanceBank;

    private String number;

    private int userId; // the id of the user

    private TreeMap<LocalDate, Double> balanceHistory = new TreeMap<>();

    public CreditCard(int userId) {
        this.userId = userId;

        // initialize balance history. It must have a date value that matches today's date.
        balanceHistory.put(LocalDate.now(), 0);
    }

    public CreditCard(String issuanceBank, String number, int userId) {
        this.issuanceBank = issuanceBank;
        this.number = number;
        this.userId = userId;

        // initialize balance history. It must have a date value that matches today's date.
        balanceHistory.put(LocalDate.now(), 0);
    }

    /**
     *
     * @param ur the user repo of this application
     * @return the name of this credit card's owner
     */
    public String getUsername(UserRepository ur) {
        User u = ur.findById(userId).orElseThrow();
        return u.getName();
    }

    // TODO: Credit card's balance history. It is a requirement that the dates in the balanceHistory 
    //       list must be in chronological order, with the most recent date appearing first in the list. 
    //       Additionally, the first object in the list must have a date value that matches today's date, 
    //       since it represents the current balance of the credit card. For example:
    //       [
    //         {date: '2023-04-13', balance: 1500},
    //         {date: '2023-04-12', balance: 1200},
    //         {date: '2023-04-11', balance: 1000},
    //         {date: '2023-04-10', balance: 800}
    //       ]

    /**
     * Given `date` and `balance`, update this credit card's balance history.
     * Update means to add `balance` to the current balance at the date specified by `date`
     * @param date
     * @param balance
     */
    public void updateBalance(LocalDate date, double balance) {
        double currentBalance = balanceHistory.getOrDefault(date, 0.0);
        balanceHistory.put(date, currentBalance + balance);
    }
}
