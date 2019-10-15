package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void factorial() {
        for (int i = 0; i < 15; i++) {
            long factorial = Factorial.factorial(i);
            long expected = factorial(i);

            assertEquals(expected, factorial, "n = " + i);
        }
    }

    public static long factorial(int n) {
        if (n <= 0)
            return 0;

        long factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }
}