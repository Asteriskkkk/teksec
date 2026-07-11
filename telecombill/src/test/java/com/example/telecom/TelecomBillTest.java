package com.example.telecom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TelecomBill Test Suite")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TelecomBillTest {

    @BeforeAll
    static void setUpSuite() {
        System.out.println("Starting TelecomBill test suite...");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Starting test execution...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Completed test execution.");
    }

    @AfterAll
    static void tearDownSuite() {
        System.out.println("Completed TelecomBill test suite.");
    }

    @Nested
    @Order(1)
    @DisplayName("1. Valid Connection Type and Billing Rules")
    class ValidInputTests {

        @Test
        @Order(1)
        @DisplayName("validateConnectionType should accept Prepaid in mixed letter cases")
        void testValidateConnectionTypePrepaidVariousCases() {
            assertTrue(TelecomBill.validateConnectionType("Prepaid"));
            assertTrue(TelecomBill.validateConnectionType("prepaid"));
            assertTrue(TelecomBill.validateConnectionType("PREPAID"));
            assertTrue(TelecomBill.validateConnectionType("PrEpAiD"));
        }

        @Test
        @Order(2)
        @DisplayName("validateConnectionType should accept Postpaid in mixed letter cases")
        void testValidateConnectionTypePostpaidVariousCases() {
            assertTrue(TelecomBill.validateConnectionType("Postpaid"));
            assertTrue(TelecomBill.validateConnectionType("postpaid"));
            assertTrue(TelecomBill.validateConnectionType("POSTPAID"));
            assertTrue(TelecomBill.validateConnectionType("PoStPaId"));
        }

        @Test
        @Order(3)
        @DisplayName("calculateBillAmount should apply rate 1.50 below 100 units")
        void testCalculateBillAmountBelow100() {
            assertEquals(148.50, TelecomBill.calculateBillAmount(99), 0.0001);
            assertEquals(1.50, TelecomBill.calculateBillAmount(1), 0.0001);
        }

        @Test
        @Order(4)
        @DisplayName("calculateBillAmount should apply rate 2.00 for 100 to 499 units")
        void testCalculateBillAmountBetween100And499() {
            assertEquals(200.00, TelecomBill.calculateBillAmount(100), 0.0001);
            assertEquals(998.00, TelecomBill.calculateBillAmount(499), 0.0001);
        }

        @Test
        @Order(5)
        @DisplayName("calculateBillAmount should apply rate 3.50 for 500 or more units")
        void testCalculateBillAmount500OrMore() {
            assertEquals(1750.00, TelecomBill.calculateBillAmount(500), 0.0001);
            assertEquals(2100.00, TelecomBill.calculateBillAmount(600), 0.0001);
        }
    }

    @Nested
    @Order(2)
    @DisplayName("2. Invalid and Edge Case Scenarios")
    class InvalidAndEdgeCaseTests {

        @Test
        @Order(6)
        @DisplayName("validateConnectionType should reject unknown and special formats")
        void testValidateConnectionTypeInvalidInputs() {
            assertFalse(TelecomBill.validateConnectionType("Corporate"));
            assertFalse(TelecomBill.validateConnectionType("Pre Paid"));
            assertFalse(TelecomBill.validateConnectionType("post_paid"));
            assertFalse(TelecomBill.validateConnectionType("123"));
            assertFalse(TelecomBill.validateConnectionType(""));
        }

        @Test
        @Order(7)
        @DisplayName("calculateBillAmount should return 0 for zero or negative units")
        void testCalculateBillAmountZeroOrNegative() {
            assertEquals(0.0, TelecomBill.calculateBillAmount(0), 0.0001);
            assertEquals(0.0, TelecomBill.calculateBillAmount(-1), 0.0001);
            assertEquals(0.0, TelecomBill.calculateBillAmount(-100), 0.0001);
        }
    }
}
