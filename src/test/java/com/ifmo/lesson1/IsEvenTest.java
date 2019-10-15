package com.ifmo.lesson1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class IsEvenTest {

    @Test
    void isEven() {
        var rnd = new Random(0);

        for (int i = 0; i < 10; i++) {
            int n = rnd.nextInt(100);

            boolean even = IsEven.isEven(n);
            boolean expected = n % 2 == 0;

            assertEquals(expected, even, "n = " + n);
        }
    }
}