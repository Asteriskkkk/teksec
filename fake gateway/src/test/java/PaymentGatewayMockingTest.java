import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class PaymentGatewayMockingTest {

    private PaymentClient paymentClient;

    @BeforeEach
    void setUp() {
        paymentClient = null;
    }

    @AfterEach
    void tearDown() {
        paymentClient = null;
    }

    @Test
    void test01_mockDefaultConstructor() {
        try (MockedConstruction<PaymentGateway> construction = mockConstruction(PaymentGateway.class, (mock, context) -> {
            when(mock.makePayment(100.0)).thenReturn("Fake Cash Payment");
        })) {
            paymentClient = new PaymentClient();

            assertEquals("Fake Cash Payment", paymentClient.process(100.0));
            assertEquals(1, construction.constructed().size());
        }
    }

    @Test
    void test02_mockParameterizedConstructor() {
        try (MockedConstruction<PaymentGateway> construction = mockConstruction(PaymentGateway.class, (mock, context) -> {
            when(mock.makePayment(250.0)).thenReturn("Simulated Stripe Response");
        })) {
            paymentClient = new PaymentClient("Stripe");

            assertEquals("Simulated Stripe Response", paymentClient.process(250.0));
            assertEquals(1, construction.constructed().size());
        }
    }

    @Test
    void test03_mockMultipleConstructors() {
        try (MockedConstruction<PaymentGateway> construction = mockConstruction(PaymentGateway.class)) {
            PaymentClient cashClient = new PaymentClient();
            PaymentClient stripeClient = new PaymentClient("Stripe");
            PaymentClient paypalClient = new PaymentClient("PayPal");

            assertNull(cashClient.process(10.0));
            assertNull(stripeClient.process(20.0));
            assertNull(paypalClient.process(30.0));
            assertEquals(3, construction.constructed().size());
        }
    }

    @Test
    void test04_mockMethod() {
        PaymentGateway gateway = mock(PaymentGateway.class);
        when(gateway.makePayment(75.0)).thenReturn("Mocked Payment Success");

        paymentClient = new PaymentClient(gateway);

        assertEquals("Mocked Payment Success", paymentClient.process(75.0));
    }
}