package com.example.zarabank.controller;

import com.example.zarabank.dto.AccountRequest;
import com.example.zarabank.dto.AccountResponse;
import com.example.zarabank.entity.Account;
import com.example.zarabank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/open")
    public ResponseEntity<Map<String, Object>> openAccount(@RequestBody AccountRequest request) {
        log.info("REST API: Open account request received for: {}", request.getAccountNumber());
        try {
            Account account = accountService.createAccount(request);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Account opened successfully");
            response.put("account", accountService.mapToResponse(account));
            log.info("Account opened successfully: {}", request.getAccountNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            log.error("Error opening account: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Map<String, Object>> getAccount(@PathVariable String accountNumber) {
        log.info("REST API: Get account request for: {}", accountNumber);
        try {
            AccountResponse accountResponse = accountService.getAccountResponse(accountNumber);
            Map<String, Object> response = new HashMap<>();
            response.put("account", accountResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error retrieving account: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAccounts() {
        log.info("REST API: Get all accounts request");
        try {
            List<AccountResponse> accounts = accountService.getAllAccountsResponse();
            Map<String, Object> response = new HashMap<>();
            response.put("accounts", accounts);
            response.put("count", accounts.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error retrieving accounts: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/by-card-type/{cardType}")
    public ResponseEntity<Map<String, Object>> getAccountsByCardType(@PathVariable String cardType) {
        log.info("REST API: Get accounts by card type: {}", cardType);
        try {
            List<Account> accounts = accountService.getAccountsByAtmCardType(cardType);
            Map<String, Object> response = new HashMap<>();
            response.put("cardType", cardType);
            response.put("accounts", accounts.stream()
                    .map(accountService::mapToResponse)
                    .toList());
            response.put("count", accounts.size());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("Invalid card type: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
