package io.gezzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    public int add(int a, int b) {
        LOGGER.info("sumando");
        return a + b;
    }

    public int subtract(int a, int b) {
        LOGGER.debug("restando");
        return a - b;
    }

    public int divide(int a, int b) {
        LOGGER.trace("dividiendo");
        if (b == 0) {
            LOGGER.error("Division by zero is not allowed");
            throw new IllegalArgumentException("Division by zero is not allowed");
        }

        return a / b;
    }
}
