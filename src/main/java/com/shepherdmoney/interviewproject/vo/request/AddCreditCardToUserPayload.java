package com.shepherdmoney.interviewproject.vo.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddCreditCardToUserPayload {

    private int userId; // to access userId, call getUserId()

    private String cardIssuanceBank;

    private String cardNumber;
}
