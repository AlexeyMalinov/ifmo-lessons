package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntsOrderingTest {

    @Test
    void ordering() {
        int[][] vals = new int[][]{
                new int[]{3, 9, -1},
                new int[]{2, 4, 3},
                new int[]{7, 0, -5},
                new int[]{23, 6, 2},
                new int[]{5, 11, 98},
        };

        int[][] expected = new int[][]{
                new int[]{-1, 3, 9},
                new int[]{2, 3, 4},
                new int[]{-5, 0, 7},
                new int[]{2, 6, 23},
                new int[]{5, 11, 98}
        };

        for (int i = 0; i < vals.length; i++) {
            String ordering = IntsOrdering.ordering(vals[i][0], vals[i][1], vals[i][2]);

            assertEquals("Числа в переменных a, b и c: " + vals[i][0] + ", " + vals[i][1] + ", " + vals[i][2] + "\n" +
                    "Возрастающая последовательность: " + expected[i][0] + ", " + expected[i][1] + ", " + expected[i][2],
                    ordering);
        }
    }
}