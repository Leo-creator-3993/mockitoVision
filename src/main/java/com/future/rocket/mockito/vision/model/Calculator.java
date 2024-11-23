package com.future.rocket.mockito.vision.model;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }

        return a / b;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
