package com.example.zarabank.service;

import com.example.zarabank.dto.AtmCardRequest;
import com.example.zarabank.entity.AtmCard;
import com.example.zarabank.entity.Account;
import com.example.zarabank.exception.AccountNotFoundException;
import com.example.zarabank.exception.CardAlreadyIssuedException;
import com.example.zarabank.repository.AccountRepository;
import com.example.zarabank.repository.AtmCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AtmCardService {

    private final AtmCardRepository atmCardRepository;
    private final AccountRepository accountRepository;

    public AtmCardService(AtmCardRepository atmCardRepository, AccountRepository accountRepository) {
        this.atmCardRepository = atmCardRepository;
        this.accountRepository = accountRepository;
    }

    public AtmCard issueCard(AtmCardRequest request) {
        log.info("Issuing ATM card for account: {}", request.getAccountNumber());

        // Check if account exists
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> {
                    log.error("Account not found: {}", request.getAccountNumber());
                    return new AccountNotFoundException(request.getAccountNumber());
                });

        // Check if card already issued
        if (atmCardRepository.existsByAccountId(account.getId())) {
            log.warn("Card already issued for account: {}", request.getAccountNumber());
            throw new CardAlreadyIssuedException(request.getAccountNumber());
        }

        // Validate card type
        String cardTypeStr = request.getCardType().toUpperCase();
        AtmCard.CardType cardType;
        try {
            cardType = AtmCard.CardType.valueOf(cardTypeStr);
        } catch (IllegalArgumentException e) {
            log.error("Invalid card type: {}", request.getCardType());
            throw new IllegalArgumentException("Invalid card type. Must be VISA, MASTER, RUPAY, or MAESTRO.");
        }

        // Create and save ATM card
        AtmCard atmCard = new AtmCard();
        atmCard.setCardNumber(request.getCardNumber());
        atmCard.setCardType(cardType);
        atmCard.setExpiryDate(request.getExpiryDate());
        atmCard.setCvv(request.getCvv());
        atmCard.setAccount(account);

        atmCard = atmCardRepository.save(atmCard);
        log.info("ATM card issued successfully. Card number: {}, Account: {}", request.getCardNumber(), request.getAccountNumber());

        return atmCard;
    }

    public AtmCard getCardByNumber(String cardNumber) {
        log.info("Retrieving ATM card: {}", cardNumber);
        return atmCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> {
                    log.error("Card not found: {}", cardNumber);
                    return new IllegalArgumentException("ATM card not found: " + cardNumber);
                });
    }
}
