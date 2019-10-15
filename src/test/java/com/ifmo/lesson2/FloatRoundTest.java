package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FloatRoundTest {

    @Test
    void round() {
        var rnd = new Random(0);

        for (int i = 0; i < 10; i++) {
            float n = rnd.nextFloat() + rnd.nextInt(10);

            n = rnd.nextBoolean() ? -n : n;

            float rounded = FloatRound.round(n);

            int expected = Math.round(n);

            assertEquals(expected, rounded, "n = " + n);
        }
    }
}