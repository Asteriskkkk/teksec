package com.example.accountsystem.model;

public class Account {

    private final String accountId;
    private final String accountHolderName;
    private final double balance;
    private final Loan loan;

    public Account(String accountId, String accountHolderName, double balance, Loan loan) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.loan = loan;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public Loan getLoan() {
        return loan;
    }
}
