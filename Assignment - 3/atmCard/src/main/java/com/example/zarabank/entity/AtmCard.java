package com.example.zarabank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "atm_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;

    @Column(name = "card_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    public enum CardType {
        VISA, MASTER, RUPAY, MAESTRO
    }
}
