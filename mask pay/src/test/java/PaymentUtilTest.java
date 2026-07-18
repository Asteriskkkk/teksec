import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggingExtension.class)
class PaymentUtilTest {

    private PaymentUtil paymentUtil;

    @BeforeEach
    void setUp() {
        paymentUtil = new PaymentUtil();
        System.out.println("Test setup complete");
    }

    @AfterEach
    void tearDown() {
        paymentUtil = null;
        System.out.println("Test teardown complete");
    }

    @Test
    void maskCardNumberShouldReturnMaskedValueForValidCardNumber() {
        assertEquals("**** **** **** 5678", paymentUtil.maskCardNumber("1234567812345678"), "The last four digits should remain visible.");
    }

    @Test
    void maskCardNumberShouldReturnInvalidMessageForNullInput() {
        assertEquals("Invalid card number", paymentUtil.maskCardNumber(null), "Null card numbers should be rejected.");
    }

    @Test
    void maskCardNumberShouldReturnInvalidMessageForShortInput() {
        assertEquals("Invalid card number", paymentUtil.maskCardNumber("123"), "Card numbers shorter than four characters should be rejected.");
    }

    @Test
    void isCardExpiredShouldReturnTrueForPastDate() {
        assertTrue(paymentUtil.isCardExpired(LocalDate.now().minusDays(1)), "Past expiry dates should be treated as expired.");
    }

    @Test
    void isCardExpiredShouldReturnFalseForToday() {
        assertFalse(paymentUtil.isCardExpired(LocalDate.now()), "Today's date should not be considered expired.");
    }

    @Test
    void isCardExpiredShouldReturnFalseForFutureDate() {
        assertFalse(paymentUtil.isCardExpired(LocalDate.now().plusDays(10)), "Future expiry dates should not be considered expired.");
    }

    @Test
    void isCardExpiredShouldThrowNullPointerExceptionForNullInput() {
        assertThrows(NullPointerException.class, () -> paymentUtil.isCardExpired(null), "Null expiry dates should raise an exception.");
    }

    @Test
    void formatCurrencyShouldFormatValueToTwoDecimalPlaces() {
        assertEquals("$1234.50", paymentUtil.formatCurrency(1234.5, "$"), "Currency values should be formatted to two decimals.");
    }

    @Test
    void formatCurrencyShouldSupportEuroSymbol() {
        assertEquals("€1234.50", paymentUtil.formatCurrency(1234.5, "€"), "Euro values should be prefixed correctly.");
    }

    @Test
    void formatCurrencyShouldSupportPoundSymbol() {
        assertEquals("£1234.50", paymentUtil.formatCurrency(1234.5, "£"), "Pound values should be prefixed correctly.");
    }

    @Test
    void formatCurrencyShouldSupportRupeeSymbol() {
        assertEquals("₹1234.50", paymentUtil.formatCurrency(1234.5, "₹"), "Rupee values should be prefixed correctly.");
    }

    @Test
    void formatCurrencyShouldRoundHalfUpStyleToTwoDecimals() {
        assertEquals("$1234.57", paymentUtil.formatCurrency(1234.567, "$"), "Values should be rounded to two decimals.");
    }

    @Test
    void formatCurrencyShouldHandleZeroAndNegativeAmounts() {
        assertEquals("$0.00", paymentUtil.formatCurrency(0, "$"), "Zero should format as two decimal places.");
        assertEquals("$-45.20", paymentUtil.formatCurrency(-45.2, "$"), "Negative amounts should preserve sign and formatting.");
    }

    @Test
    void formatCurrencyShouldThrowNullPointerExceptionForNullCurrencySymbol() {
        assertThrows(NullPointerException.class, () -> paymentUtil.formatCurrency(100.0, null), "Null currency symbols should raise an exception.");
    }
}