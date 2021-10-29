package ru.otus.testing.services;

import ru.otus.testing.annotation.AfterEach;
import ru.otus.testing.annotation.BeforeEach;
import ru.otus.testing.annotation.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    public void setUp(){
        System.out.println("Message before test");
        calculator = new Calculator();
    }

    @Test
    public void testMultiply() {
        System.out.println("-- testMultiply --");
        assertEquals(20, calculator.multiply(4, 5));
    }

    @Test
    void testMultiplyWithZero() {
        System.out.println("-- testMultiplyWithZero --");
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(0, calculator.multiply(5, 0));
    }

    @AfterEach
    public void clear(){
        System.out.println("Message after test");
        calculator = null;
    }
}