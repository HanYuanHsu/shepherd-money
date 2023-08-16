package com.shepherdmoney.interviewproject.controller;

import com.shepherdmoney.interviewproject.model.CreditCard;
import com.shepherdmoney.interviewproject.model.User;
import com.shepherdmoney.interviewproject.repository.UserRepository;
import com.shepherdmoney.interviewproject.vo.request.AddCreditCardToUserPayload;
import com.shepherdmoney.interviewproject.vo.request.UpdateBalancePayload;
import com.shepherdmoney.interviewproject.vo.response.CreditCardView;

import com.shepherdmoney.interviewproject.repository.CreditCardRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// logging
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class CreditCardController {

    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(CreditCardController.class.getName());
    //Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    public CreditCardController(CreditCardRepository ccr, UserRepository ur) {
        this.creditCardRepository = ccr;
        this.userRepository = ur;
    }

    /**
     * Creates a credit card entity, and then associate that credit card with user with given userId
     * @param payload
     * @return A ResponseEntity with an appropriate HTTP status code and response body.
     *          - 200 OK: If the user exists and the credit card is successfully associated with the user.
     *          - 404 Not Found: If the user with the given userId does not exist.
     *          - 500 Internal Server Error: If an unexpected error occurs during the process.
     */
    @PostMapping("/credit-card")
    public ResponseEntity<Integer> addCreditCardToUser(@RequestBody AddCreditCardToUserPayload payload) {

        try {
            User user = userRepository.findById(payload.getUserId()).orElse(null);

            // if user does not exist, return 404
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            CreditCard card = new CreditCard(payload.getUserId());
            user.addCreditCard(card.getId());
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred", e);
            return ResponseEntity.internalServerError().build();
        }



/*
        for (int cid : user.getCreditCards()) {
            System.out.println(cid);
        }

 */
    }

    @GetMapping("/credit-card:all")
    public ResponseEntity<List<CreditCardView>> getAllCardOfUser(@RequestParam int userId) {
        // TODO: return a list of all credit cards associated with the given userId, using CreditCardView class
        //       if the user has no credit card, return empty list, never return null
        return null;
    }

    @GetMapping("/credit-card:user-id")
    public ResponseEntity<Integer> getUserIdForCreditCard(@RequestParam String creditCardNumber) {
        // TODO: Given a credit card number, efficiently find whether there is a user associated with the credit card
        //       If so, return the user id in a 200 OK response. If no such user exists, return 400 Bad Request
        return null;
    }

    /*
    @PostMapping("/credit-card:update-balance")
    public SomeEnityData postMethodName(@RequestBody UpdateBalancePayload[] payload) {
        //TODO: Given a list of transactions, update credit cards' balance history.
        //      For example: if today is 4/12, a credit card's balanceHistory is [{date: 4/12, balance: 110}, {date: 4/10, balance: 100}],
        //      Given a transaction of {date: 4/10, amount: 10}, the new balanceHistory is
        //      [{date: 4/12, balance: 120}, {date: 4/11, balance: 110}, {date: 4/10, balance: 110}]
        //      Return 200 OK if update is done and successful, 400 Bad Request if the given card number
        //        is not associated with a card.
        
        return null;
    }

     */
    
}
