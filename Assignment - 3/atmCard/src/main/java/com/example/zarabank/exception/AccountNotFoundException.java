package com.example.zarabank.exception;

public class AccountNotFoundException extends BankingException {

    public AccountNotFoundException(String accountNumber) {
        super("Account not found: " + accountNumber);
    }
}
