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

import java.time.Instant;
import java.util.List;

@Service
public class BalanceHistoryService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public boolean updateCardBalanceHelper(UpdateBalancePayload payload) {
        String creditCardNumber = payload.getCreditCardNumber();

        CreditCard card = creditCardRepository.findByNumber(creditCardNumber).orElse(null);
        if (card == null) {
            return false;
        }

        Instant payloadTime = payload.getTransactionTime();
        double payloadAmount = payload.getTransactionAmount();
        List<BalanceHistory> balanceHistoryList = card.getBalanceHistory();

        for (BalanceHistory currentBalanceHistory : balanceHistoryList) {
            Instant currentDate = currentBalanceHistory.getDate();
            int cmp = payloadTime.compareTo(currentDate);
            // cmp > 0 means that payload is more recent
            // cmp < 0 means that the current balance history is more recent
            if (cmp > 0) {
                // add the new balance history created from the payload
                BalanceHistory newBalanceHistory = new BalanceHistory(payloadTime, payloadAmount);
                balanceHistoryList.add(newBalanceHistory);
                break;
            } else if (cmp < 0) {
                currentBalanceHistory.addMoney(payloadAmount);
            } else {
                currentBalanceHistory.addMoney(payloadAmount);
                break;
            }
        }

        // persistence
        creditCardRepository.save(card);

        return true;
    }
}
