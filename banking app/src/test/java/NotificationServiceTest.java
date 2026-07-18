import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationServiceTest {

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService();
    }

    @AfterEach
    void tearDown() {
        notificationService = null;
    }

    @Test
    void notifyDepositShouldReturnSuccessMessage() {
        assertEquals("Deposit of 150.0 made successfully for Alice", notificationService.notifyDeposit("Alice", 150.0));
    }

    @Test
    void notifyWithdrawalShouldReturnSuccessMessage() {
        assertEquals("Withdrawal of 75.0 made successfully for Bob", notificationService.notifyWithdrawal("Bob", 75.0));
    }

    @Test
    void notifyInsufficientFundsShouldReturnFailureMessage() {
        assertEquals("Withdrawal failed due to insufficient funds for Carol", notificationService.notifyInsufficientFunds("Carol"));
    }
}