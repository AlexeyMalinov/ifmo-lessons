package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Random15Test {

    @Test
    void main() {
        new AssertNonEmptyStdout().assertNonEmpty(() -> Random15.main(new String[0]));
    }

    @Test
    void randomNumbers() {
        int[] randomNumbers = Random15.randomNumbers();

        new AssertRandomInRange().assertInRange(randomNumbers, 0, 9);
    }
}