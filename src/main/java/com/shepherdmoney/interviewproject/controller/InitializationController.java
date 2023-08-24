package com.shepherdmoney.interviewproject.controller;

import com.shepherdmoney.interviewproject.repository.CreditCardRepository;
import com.shepherdmoney.interviewproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class InitializationController {
    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public InitializationController(UserRepository ur, CreditCardRepository cr) {
        userRepository = ur;
        creditCardRepository = cr;
    }

    @DeleteMapping("/initialize")
    public void initialize() {
        userRepository.deleteAll();
        creditCardRepository.deleteAll();
    }
}
