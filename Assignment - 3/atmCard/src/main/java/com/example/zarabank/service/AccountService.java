package com.example.zarabank.service;

import com.example.zarabank.dto.AccountRequest;
import com.example.zarabank.dto.AccountResponse;
import com.example.zarabank.entity.Account;
import com.example.zarabank.entity.CurrentAccount;
import com.example.zarabank.entity.SavingsAccount;
import com.example.zarabank.exception.AccountNotFoundException;
import com.example.zarabank.repository.AccountRepository;
import com.example.zarabank.repository.CurrentAccountRepository;
import com.example.zarabank.repository.SavingsAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final SavingsAccountRepository savingsAccountRepository;
    private final CurrentAccountRepository currentAccountRepository;

    public AccountService(AccountRepository accountRepository,
                         SavingsAccountRepository savingsAccountRepository,
                         CurrentAccountRepository currentAccountRepository) {
        this.accountRepository = accountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.currentAccountRepository = currentAccountRepository;
    }

    public Account createAccount(AccountRequest request) {
        log.info("Creating new account: {}", request.getAccountNumber());

        if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            log.warn("Account already exists: {}", request.getAccountNumber());
            throw new IllegalArgumentException("Account number already exists: " + request.getAccountNumber());
        }

        Account account;
        if ("SAVINGS".equalsIgnoreCase(request.getAccountType())) {
            account = new SavingsAccount(
                    request.getAccountNumber(),
                    request.getAccountHolderName(),
                    request.getBalance(),
                    request.getMinimumBalance()
            );
            savingsAccountRepository.save((SavingsAccount) account);
            log.info("Savings account created successfully: {}", request.getAccountNumber());
        } else if ("CURRENT".equalsIgnoreCase(request.getAccountType())) {
            account = new CurrentAccount(
                    request.getAccountNumber(),
                    request.getAccountHolderName(),
                    request.getBalance(),
                    request.getOverdraftLimit()
            );
            currentAccountRepository.save((CurrentAccount) account);
            log.info("Current account created successfully: {}", request.getAccountNumber());
        } else {
            log.error("Invalid account type: {}", request.getAccountType());
            throw new IllegalArgumentException("Invalid account type. Must be SAVINGS or CURRENT.");
        }

        return account;
    }

    public Account getAccountByNumber(String accountNumber) {
        log.info("Retrieving account: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    public List<Account> getAccountsByAtmCardType(String cardType) {
        log.info("Retrieving accounts by ATM card type: {}", cardType);
        try {
            com.example.zarabank.entity.AtmCard.CardType type = com.example.zarabank.entity.AtmCard.CardType.valueOf(cardType.toUpperCase());
            List<Account> accounts = accountRepository.findAccountsByAtmCardType(type);
            log.info("Found {} accounts with card type: {}", accounts.size(), cardType);
            return accounts;
        } catch (IllegalArgumentException e) {
            log.error("Invalid card type: {}", cardType);
            throw new IllegalArgumentException("Invalid card type. Must be VISA, MASTER, RUPAY, or MAESTRO.");
        }
    }

    public AccountResponse getAccountResponse(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        return mapToResponse(account);
    }

    public AccountResponse mapToResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountHolderName(account.getAccountHolderName());
        response.setBalance(account.getBalance());

        if (account instanceof SavingsAccount savingsAccount) {
            response.setAccountType("SAVINGS");
            response.setMinimumBalance(savingsAccount.getMinimumBalance());
        } else if (account instanceof CurrentAccount currentAccount) {
            response.setAccountType("CURRENT");
            response.setOverdraftLimit(currentAccount.getOverdraftLimit());
        }

        if (account.getAtmCard() != null) {
            response.setAtmCardNumber(account.getAtmCard().getCardNumber());
            response.setAtmCardType(account.getAtmCard().getCardType().name());
        }

        return response;
    }

    public List<AccountResponse> getAllAccountsResponse() {
        return accountRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
