package io.gezzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private final StringUtils stringUtils = new StringUtils();

    @Test
    void testIsPalindromeWithPalindrome() {
        assertTrue(stringUtils.isPalindrome("A man a plan a canal Panama"),
                "Should detect palindrome ignoring spaces and case");
    }

    @Test
    void testIsPalindromeWithNonPalindrome() {
        assertFalse(stringUtils.isPalindrome("Hello World"), "Non-palindrome input should return false");
    }

    @Test
    void testIsPalindromeWithNull() {
        assertFalse(stringUtils.isPalindrome(null), "Null input should return false");
    }
}
