package com.example.palindrome;

public class StringUtils {

    public boolean isPalindrome(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equalsIgnoreCase(reversed);
    }
}
