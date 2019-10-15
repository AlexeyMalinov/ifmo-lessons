package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClosestToTenTest {

    @Test
    void closestToTen() {
        var ms = new float[]{7.45f, 23.6f, 14.7f, 1.38f, 7.7f};
        var ns = new float[]{7.46f, 5.9f, 5.5f, 3f, 14.2f};
        var expected = new float[]{7.46f, 5.9f, 5.5f, 3f, 7.7f};

        for (int i = 0; i < ms.length; i++) {
            float m = ms[i];
            float n = ns[i];
            float exp = expected[i];

            float closestToTen = ClosestToTen.closestToTen(m, n);

            assertEquals(exp, closestToTen, "m = " + m + ", n = " + n);
        }
    }
}