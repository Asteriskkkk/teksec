package com.example.palindrome;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    private StringUtils stringUtils;

    @BeforeEach
    void setUp() {
        // Initialize test dependencies before each test method.
        stringUtils = new StringUtils();
    }

    @AfterEach
    void tearDown() {
        // Clean up test dependencies after each test method.
        stringUtils = null;
    }

    @Test
    void testIsPalindromeWithValidPalindrome() {
        // Test Case 1: valid palindrome input.
        assertTrue(stringUtils.isPalindrome("Level"));
    }

    @Test
    void testIsPalindromeWithNonPalindrome() {
        // Test Case 2: non-palindrome input.
        assertFalse(stringUtils.isPalindrome("Hello"));
    }

    @Test
    void testIsPalindromeWithinTimeout() {
        // Test Case 3: verify a moderately long palindrome completes within 1 second.
        String moderatelyLongPalindrome = "AblewasIereIsawElba".repeat(200);

        assertTimeout(Duration.ofSeconds(1), () -> {
            assertTrue(stringUtils.isPalindrome(moderatelyLongPalindrome));
        });
    }

    @Test
    void testPreemptiveTimeoutForIsPalindrome() {
        // Test Case 4: verify a very long palindrome completes within 1 second preemptively.
        String veryLongPalindrome = "racecar".repeat(10_000);

        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            assertTrue(stringUtils.isPalindrome(veryLongPalindrome));
        });
    }
}
