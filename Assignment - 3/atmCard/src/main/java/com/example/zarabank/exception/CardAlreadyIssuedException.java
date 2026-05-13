package com.example.zarabank.exception;

public class CardAlreadyIssuedException extends BankingException {

    public CardAlreadyIssuedException(String accountNumber) {
        super("ATM card already issued for account: " + accountNumber);
    }
}
