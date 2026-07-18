import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionServiceTest {

    private TransactionService transactionService;
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
        bankAccount = new BankAccount("ACC2002", "Bob", 500.0);
    }

    @AfterEach
    void tearDown() {
        transactionService = null;
        bankAccount = null;
    }

    @Test
    void depositToAccountShouldReturnTrueForPositiveAmount() {
        assertTrue(transactionService.depositToAccount(bankAccount, 200.0));
        assertEquals(700.0, bankAccount.getBalance(), 0.0001);
    }

    @Test
    void depositToAccountShouldReturnFalseForNonPositiveAmount() {
        assertFalse(transactionService.depositToAccount(bankAccount, 0.0));
        assertFalse(transactionService.depositToAccount(bankAccount, -50.0));
        assertEquals(500.0, bankAccount.getBalance(), 0.0001);
    }

    @Test
    void withdrawFromAccountShouldReturnTrueWhenBalanceIsSufficient() {
        assertTrue(transactionService.withdrawFromAccount(bankAccount, 300.0));
        assertEquals(200.0, bankAccount.getBalance(), 0.0001);
    }

    @Test
    void withdrawFromAccountShouldReturnFalseWhenBalanceIsInsufficient() {
        assertFalse(transactionService.withdrawFromAccount(bankAccount, 600.0));
        assertEquals(500.0, bankAccount.getBalance(), 0.0001);
    }
}