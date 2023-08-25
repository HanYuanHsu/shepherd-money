package com.shepherdmoney.interviewproject.service;

import com.shepherdmoney.interviewproject.model.BalanceHistory;
import com.shepherdmoney.interviewproject.model.CreditCard;
import com.shepherdmoney.interviewproject.repository.CreditCardRepository;
import com.shepherdmoney.interviewproject.vo.request.UpdateBalancePayload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceHistoryService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public boolean updateCardBalanceHelper(UpdateBalancePayload payload) {
        String creditCardNumber = payload.getCreditCardNumber();

        em.getTransaction().begin();

        CreditCard card = creditCardRepository.findByNumber(creditCardNumber).orElse(null);
        if (card == null) {
            return false;
        }

        List<BalanceHistory> balanceHistory = card.getBalanceHistory();
        BalanceHistory balanceHistoryEntry = balanceHistory.get(0);
        balanceHistoryEntry.setBalance(69);

        em.persist(card);
        em.getTransaction().commit();

        return true;
    }
}
