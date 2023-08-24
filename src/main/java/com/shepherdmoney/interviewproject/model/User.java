package com.shepherdmoney.interviewproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "MyUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String email;

    // this user's credit cards.
    // A user can have one or more, or none at all. We want to be able to query credit cards by user
    // and user by a credit card.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CreditCard> creditCards;


    /* constructors */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.creditCards = new HashSet<>();
    }
    /* end constructors */


    // add a credit card to this user
    public void addCreditCard(CreditCard c) {
        creditCards.add(c);
        c.setUser(this);
    }
}
