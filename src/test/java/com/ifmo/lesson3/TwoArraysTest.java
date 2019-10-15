package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoArraysTest {

    @Test
    void main() {
        new AssertNonEmptyStdout().assertNonEmpty(() -> TwoArrays.main(new String[0]));
    }

    @Test
    void randomNumbers() {
        int[] randomNumbers = TwoArrays.randomNumbers();

        new AssertRandomInRange().assertInRange(randomNumbers, 0, 5);
    }

    @Test
    void average() {
        int[] nums = new int[]{1,2,3,4,5};

        assertEquals(3, TwoArrays.average(nums));
    }
}