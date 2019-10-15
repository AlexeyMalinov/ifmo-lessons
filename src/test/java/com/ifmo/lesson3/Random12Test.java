package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Random12Test {
    private static final int MAX = 15;
    private static final int[] ARR = new int[]{1,MAX,3,4,5,MAX,7,8,9,0,10,-15};
    private static final int MAX_LAST_INDEX = 5;

    @Test
    void main() {
        new AssertNonEmptyStdout().assertNonEmpty(() -> Random12.main(new String[0]));
    }

    @Test
    void randomNumbers() {
        int[] randomNumbers = Random12.randomNumbers();

        new AssertRandomInRange().assertInRange(randomNumbers, -15, 15);
    }

    @Test
    void max() {
        int max = Random12.max(ARR);

        assertEquals(MAX, max);
    }

    @Test
    void lastIndex() {
        int lastIndexOf = Random12.lastIndexOf(ARR, MAX);

        assertEquals(MAX_LAST_INDEX, lastIndexOf);
    }
}