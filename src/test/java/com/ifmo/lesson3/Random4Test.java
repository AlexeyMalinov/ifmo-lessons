package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Random4Test {

    @Test
    void main() {
        new AssertNonEmptyStdout().assertNonEmpty(() -> Random4.main(new String[0]));
    }

    @Test
    void randomNumbers() {
        int[] randomNumbers = Random4.randomNumbers();

        new AssertRandomInRange().assertInRange(randomNumbers, 10, 99);
    }

    @Test
    void isIncreasingSequence() {
        int[] incr = new int[]{1,2,3,4};
        int[] notIncr = new int[]{1,3,2,4};

        assertTrue(Random4.isIncreasingSequence(incr), "Must be treated as increasing sequence: " + Arrays.toString(incr));
        assertFalse(Random4.isIncreasingSequence(notIncr), "Must be treated as not increasing sequence: " + Arrays.toString(notIncr));
    }
}