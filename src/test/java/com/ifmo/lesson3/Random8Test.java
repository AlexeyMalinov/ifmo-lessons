package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Random8Test {

    @Test
    void main() {
        new AssertNonEmptyStdout().assertNonEmpty(() -> Random8.main(new String[0]));
    }

    @Test
    void randomNumbers() {
        int[] randomNumbers = Random8.randomNumbers();

        new AssertRandomInRange().assertInRange(randomNumbers, 1, 10);
    }

    @Test
    void replaceWithZeros() {
        int[] nums = new int[10];

        Arrays.fill(nums, 1);

        int[] replacedWithZeros = Random8.replaceWithZeros(nums);
        int[] expected = new int[]{1,0,1,0,1,0,1,0,1,0};

        assertArrayEquals(expected, replacedWithZeros);
    }
}