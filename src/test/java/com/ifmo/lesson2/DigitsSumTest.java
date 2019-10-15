package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitsSumTest {

    @Test
    void digitSum() {
        var ints = new int[][]{
                new int[]{12345, 15},
                new int[]{789, 24},
                new int[]{6578390, 38}
        };

        for (int[] anInt : ints) {
            int sum = DigitsSum.digitSum(anInt[0]);

            assertEquals(anInt[1], sum, "n = " + anInt[0]);
        }
    }
}