package com.test;

import com.service.NotificationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationServiceTest {

    private final NotificationService notificationService = new NotificationService();

    @Test
    void depositNotificationContainsHolderAndAmount() {
        assertEquals(
                "Deposit of 250.0 made successfully for Asha",
                notificationService.notifyDeposit("Asha", 250.0));
    }

    @Test
    void withdrawalNotificationContainsHolderAndAmount() {
        assertEquals(
                "Withdrawal of 100.0 made successfully for Asha",
                notificationService.notifyWithdrawal("Asha", 100.0));
    }

    @Test
    void insufficientFundsNotificationContainsHolder() {
        assertEquals(
                "Withdrawal failed due to insufficient funds for Asha",
                notificationService.notifyInsufficientFunds("Asha"));
    }
}
