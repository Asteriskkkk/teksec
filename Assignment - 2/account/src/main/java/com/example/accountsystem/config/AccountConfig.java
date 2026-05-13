package com.example.accountsystem.config;

import com.example.accountsystem.model.Account;
import com.example.accountsystem.model.Loan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    public Account accountOne() {
        return new Account(
                "ACC-1001",
                "Alice Johnson",
                12500.75,
                new Loan("Home Loan", 250000.00)
        );
    }

    @Bean
    public Account accountTwo() {
        return new Account(
                "ACC-1002",
                "Brian Thomas",
                8420.50,
                new Loan("Personal Loan", 50000.00)
        );
    }

    @Bean
    public Account accountThree() {
        return new Account(
                "ACC-1003",
                "Carla Smith",
                19230.00,
                new Loan("Education Loan", 120000.00)
        );
    }
}
