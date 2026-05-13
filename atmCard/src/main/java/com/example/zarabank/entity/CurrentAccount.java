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
@DiscriminatorValue("CURRENT")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAccount extends Account {

    @Column(name = "overdraft_limit", nullable = false)
    private BigDecimal overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, BigDecimal balance, BigDecimal overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }
}
