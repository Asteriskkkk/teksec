import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount("ACC1001", "Alice", 1000.0);
    }

    @AfterEach
    void tearDown() {
        bankAccount = null;
    }

    @Test
    void constructorShouldInitializeAccountDetails() {
        assertEquals("ACC1001", bankAccount.getAccountNumber());
        assertEquals("Alice", bankAccount.getAccountHolder());
        assertEquals(1000.0, bankAccount.getBalance(), 0.0001);
    }

    @Test
    void depositShouldIncreaseBalance() {
        bankAccount.deposit(250.0);

        assertEquals(1250.0, bankAccount.getBalance(), 0.0001);
    }

    @Test
    void withdrawShouldReturnTrueWhenFundsAreAvailable() {
        assertTrue(bankAccount.withdraw(400.0));
        assertEquals(600.0, bankAccount.getBalance(), 0.0001);
    }

    @Test
    void withdrawShouldReturnFalseWhenFundsAreInsufficient() {
        assertFalse(bankAccount.withdraw(1500.0));
        assertEquals(1000.0, bankAccount.getBalance(), 0.0001);
    }
}