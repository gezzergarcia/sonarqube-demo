package io.gezzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    public boolean isPalindrome(String input) {

        LOGGER.trace("validando palíndromo");

        if (input == null) {
            return false;
        }
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        LOGGER.trace("cleaned {}", cleaned);
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
}