package com.example.accountsystem;

import com.example.accountsystem.config.AccountConfig;
import com.example.accountsystem.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountSystemApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AccountConfig.class)) {
            Account accountOne = context.getBean("accountOne", Account.class);
            Account accountTwo = context.getBean("accountTwo", Account.class);
            Account accountThree = context.getBean("accountThree", Account.class);

            printAccountDetails(accountOne);
            printAccountDetails(accountTwo);
            printAccountDetails(accountThree);
        }
    }

    private static void printAccountDetails(Account account) {
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Account Holder: " + account.getAccountHolderName());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Loan Type: " + account.getLoan().getLoanType());
        System.out.println("Loan Amount: " + account.getLoan().getLoanAmount());
        System.out.println("----------------------------------");
    }
}
