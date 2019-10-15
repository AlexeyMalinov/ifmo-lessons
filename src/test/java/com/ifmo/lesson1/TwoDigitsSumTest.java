package com.ifmo.lesson1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TwoDigitsSumTest {

    @Test
    void sum() {
        var rnd = new Random(0);

        for (int i = 0; i < 5; i++) {
            int n = rnd.nextInt(89) + 10;

            int expected = n / 10 + n % 10;

            int sum = TwoDigitsSum.sum(n);

            assertEquals(expected, sum, "n = " + n);
        }
    }
}