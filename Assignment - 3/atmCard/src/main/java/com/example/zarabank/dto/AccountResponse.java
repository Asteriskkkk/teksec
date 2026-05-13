package com.example.zarabank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("accountType")
    private String accountType;

    @JsonProperty("minimumBalance")
    private BigDecimal minimumBalance;

    @JsonProperty("overdraftLimit")
    private BigDecimal overdraftLimit;

    @JsonProperty("atmCardNumber")
    private String atmCardNumber;

    @JsonProperty("atmCardType")
    private String atmCardType;
}
