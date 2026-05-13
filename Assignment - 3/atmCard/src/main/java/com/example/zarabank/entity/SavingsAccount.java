package com.example.zarabank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SAVINGS")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount extends Account {

    @Column(name = "minimum_balance", nullable = false)
    private BigDecimal minimumBalance;

    public SavingsAccount(String accountNumber, String accountHolderName, BigDecimal balance, BigDecimal minimumBalance) {
        super(accountNumber, accountHolderName, balance);
        this.minimumBalance = minimumBalance;
    }
}
