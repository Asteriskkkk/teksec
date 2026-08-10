package com.test;

import com.model.BankAccount;
import com.service.TransactionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionServiceTest {

    @Test
    void depositToAccountAcceptsPositiveAmount() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);
        TransactionService service = new TransactionService();

        assertTrue(service.depositToAccount(account, 150.0));
        assertEquals(650.0, account.getBalance());
    }

    @Test
    void depositToAccountRejectsZeroAmount() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);
        TransactionService service = new TransactionService();

        assertFalse(service.depositToAccount(account, 0.0));
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void depositToAccountRejectsNegativeAmount() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);
        TransactionService service = new TransactionService();

        assertFalse(service.depositToAccount(account, -150.0));
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void withdrawFromAccountSucceedsWithSufficientFunds() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);
        TransactionService service = new TransactionService();

        assertTrue(service.withdrawFromAccount(account, 200.0));
        assertEquals(300.0, account.getBalance());
    }

    @Test
    void withdrawFromAccountFailsWithInsufficientFunds() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);
        TransactionService service = new TransactionService();

        assertFalse(service.withdrawFromAccount(account, 600.0));
        assertEquals(500.0, account.getBalance());
    }
}
