package com.example.zarabank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("accountType")
    private String accountType; // "SAVINGS" or "CURRENT"

    @JsonProperty("minimumBalance")
    private BigDecimal minimumBalance; // For Savings accounts

    @JsonProperty("overdraftLimit")
    private BigDecimal overdraftLimit; // For Current accounts
}
