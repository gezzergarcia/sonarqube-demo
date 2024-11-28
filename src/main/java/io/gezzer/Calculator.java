package io.gezzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    public int add(int a, int b) {
        LOGGER.trace("sumando {} + {}",a, b);
        return a + b;
    }

    public int subtract(int a, int b) {
        LOGGER.trace("restando {} + {}", a, b);
        return a - b;
    }

    public int divide(int a, int b) {
        LOGGER.trace("dividiendo {} entre {}",a, b);
        if (b == 0) {
            LOGGER.warn("División entre 0 no está permitida");
            LOGGER.error("Division by zero is not allowed");
            throw new IllegalArgumentException("Division by zero is not allowed");
        }

        return a / b;
    }
}
