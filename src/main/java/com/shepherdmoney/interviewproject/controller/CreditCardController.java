package com.shepherdmoney.interviewproject.controller;

import com.shepherdmoney.interviewproject.model.CreditCard;
import com.shepherdmoney.interviewproject.model.User;
import com.shepherdmoney.interviewproject.repository.UserRepository;
import com.shepherdmoney.interviewproject.service.CreditCardService;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedList;
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

    @Autowired
    private CreditCardService creditCardService;

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
            System.out.println("------------------- addCreditCardToUser");
            User user = userRepository.findById(payload.getUserId()).orElse(null);

            // if user does not exist, return 404
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            CreditCard card = new CreditCard(payload.getCardIssuanceBank(), payload.getCardNumber(), user);
            creditCardService.addCreditCardToUser(card, user);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Returns a list of all credit cards associated with the given userId, using CreditCardView class.
     * If the user has no credit card, return empty list, never return null
     *
     * @param userId
     * @return A ResponseEntity with an appropriate HTTP status code and response body.
     *      - 200 ok: if the user exists. The response contains a list of all credit cards associated with the given userId.
     *      - 404 not found: if no such user with the given userId exists.
     *      - 500 Internal Server Error: If an unexpected error occurs during the process.
     */
    @GetMapping("/credit-card:all")
    public ResponseEntity<List<CreditCardView>> getAllCardOfUser(@RequestParam int userId) {
        try {
            User user = userRepository.findById(userId).orElse(null);

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            List<CreditCardView> result = new ArrayList<>();
            for (CreditCard card : user.getCreditCards()) {
                CreditCardView creditCardView = new CreditCardView(card.getIssuanceBank(), card.getNumber());
                result.add(creditCardView);
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Given a credit card number, find whether there is a user associated with the credit card.
     * @param creditCardNumber
     * @return A ResponseEntity with an appropriate HTTP status code and response body.
     *      - 200 ok: if such a user exists. The response will contain the user id
     *      - 400 Bad Request: if no such user exists
     */
    @GetMapping("/credit-card:user-id")
    public ResponseEntity<Integer> getUserIdForCreditCard(@RequestParam String creditCardNumber) {
        CreditCard creditCard = creditCardRepository.findByNumber(creditCardNumber).orElse(null);

        if (creditCard != null) {
            return ResponseEntity.ok(creditCard.getUser().getId()); // 200 OK with user id
        } else {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
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




    @PostMapping("/credit-card:update-balance")
    public ResponseEntity<String> updateCardBalance(@RequestBody UpdateBalancePayload[] payload) {
        String cardNumber = payload.getCreditCardNumber();
        CreditCard card = creditCardRepository.findByNumber(cardNumber).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "credit card not found"));

        return null;
    }

    
}
