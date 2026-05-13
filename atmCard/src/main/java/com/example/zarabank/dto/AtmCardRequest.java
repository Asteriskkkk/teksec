package com.example.zarabank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmCardRequest {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("cardType")
    private String cardType; // VISA, MASTER, RUPAY, MAESTRO

    @JsonProperty("expiryDate")
    private LocalDate expiryDate;

    @JsonProperty("cvv")
    private String cvv;
}
