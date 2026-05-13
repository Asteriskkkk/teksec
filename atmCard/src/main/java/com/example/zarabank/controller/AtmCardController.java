package com.example.zarabank.controller;

import com.example.zarabank.dto.AtmCardRequest;
import com.example.zarabank.entity.AtmCard;
import com.example.zarabank.service.AtmCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/atm-cards")
@Slf4j
public class AtmCardController {

    private final AtmCardService atmCardService;

    public AtmCardController(AtmCardService atmCardService) {
        this.atmCardService = atmCardService;
    }

    @PostMapping("/issue")
    public ResponseEntity<Map<String, Object>> issueCard(@RequestBody AtmCardRequest request) {
        log.info("REST API: Issue ATM card request for account: {}", request.getAccountNumber());
        try {
            AtmCard atmCard = atmCardService.issueCard(request);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "ATM card issued successfully");
            response.put("cardNumber", atmCard.getCardNumber());
            response.put("cardType", atmCard.getCardType().name());
            response.put("expiryDate", atmCard.getExpiryDate());
            response.put("accountNumber", atmCard.getAccount().getAccountNumber());
            log.info("ATM card issued successfully: {}", request.getCardNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            log.error("Error issuing card: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            log.error("Error issuing card: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<Map<String, Object>> getCard(@PathVariable String cardNumber) {
        log.info("REST API: Get ATM card request for: {}", cardNumber);
        try {
            AtmCard atmCard = atmCardService.getCardByNumber(cardNumber);
            Map<String, Object> response = new HashMap<>();
            response.put("cardNumber", atmCard.getCardNumber());
            response.put("cardType", atmCard.getCardType().name());
            response.put("expiryDate", atmCard.getExpiryDate());
            response.put("accountNumber", atmCard.getAccount().getAccountNumber());
            response.put("accountHolderName", atmCard.getAccount().getAccountHolderName());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error retrieving card: {}", e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
