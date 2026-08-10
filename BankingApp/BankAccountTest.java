package com.test;

import com.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {

    @Test
    void createsAccountWithSuppliedDetails() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);

        assertEquals("ACC-101", account.getAccountNumber());
        assertEquals("Asha", account.getAccountHolder());
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);

        account.deposit(250.0);

        assertEquals(750.0, account.getBalance());
    }

    @Test
    void withdrawWithSufficientFundsReducesBalance() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);

        assertTrue(account.withdraw(200.0));
        assertEquals(300.0, account.getBalance());
    }

    @Test
    void withdrawMoreThanBalanceFails() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);

        assertFalse(account.withdraw(600.0));
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void withdrawZeroOrNegativeAmountFails() {
        BankAccount account = new BankAccount("ACC-101", "Asha", 500.0);

        assertFalse(account.withdraw(0.0));
        assertFalse(account.withdraw(-50.0));
        assertEquals(500.0, account.getBalance());
    }
}
