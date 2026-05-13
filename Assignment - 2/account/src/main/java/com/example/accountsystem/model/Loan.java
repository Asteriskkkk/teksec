package com.example.accountsystem.model;

public class Loan {

    private final String loanType;
    private final double loanAmount;

    public Loan(String loanType, double loanAmount) {
        this.loanType = loanType;
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }
}
