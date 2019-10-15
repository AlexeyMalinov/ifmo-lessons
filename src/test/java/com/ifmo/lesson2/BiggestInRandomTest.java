package com.ifmo.lesson2;

import com.ifmo.fun.AssertRandomInterval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BiggestInRandomTest {

    @Test
    void threeDigitRandom() {
        new AssertRandomInterval(100, 999, BiggestInRandom::threeDigitRandom)
                .assertInterval();
    }

    @Test
    void largestDigit() {
        int[] vals = new int[]{123, 475, 900};
        int[] expected = new int[]{3, 7, 9};

        for (int i = 0; i < vals.length; i++) {
            String largestDigit = BiggestInRandom.largestDigit(vals[i]);

            assertEquals("В числе " + vals[i] + " наибольшая цифра " + expected[i] + ".", largestDigit);
        }
    }
}