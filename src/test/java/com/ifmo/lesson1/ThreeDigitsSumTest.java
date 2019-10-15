package com.ifmo.lesson1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeDigitsSumTest {

    @Test
    void sum() {
        var ns = new int[]{123, 478, 2, 999, 555};
        var expected = new int[]{6, 19, 2, 27, 15};

        for (int i = 0; i < ns.length; i++) {
            int sum = ThreeDigitsSum.sum(ns[i]);

            assertEquals(expected[i], sum, "n = " + ns[i]);
        }
    }
}