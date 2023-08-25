package com.shepherdmoney.interviewproject.service;

import com.shepherdmoney.interviewproject.model.BalanceHistory;
import com.shepherdmoney.interviewproject.model.CreditCard;
import com.shepherdmoney.interviewproject.repository.CreditCardRepository;
import com.shepherdmoney.interviewproject.vo.request.UpdateBalancePayload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceHistoryService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public boolean updateCardBalanceHelper(UpdateBalancePayload payload) {
        String creditCardNumber = payload.getCreditCardNumber();

        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        //EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();

        CreditCard card = creditCardRepository.findByNumber(creditCardNumber).orElse(null);
        if (card == null) {
            return false;
        }

        List<BalanceHistory> balanceHistory = card.getBalanceHistory();
//
//        for (BalanceHistory balanceHistoryEntry : balanceHistory) {
//
//        }

        BalanceHistory balanceHistoryEntry = balanceHistory.get(0);//TEMPORAY UPDATE?
        balanceHistoryEntry.setDate(payload.getTransactionTime());
        balanceHistoryEntry.setBalance(payload.getTransactionAmount());

        creditCardRepository.save(card);

        //em.persist(card);
        //em.getTransaction().commit();

        return true;
    }
}
