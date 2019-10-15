package com.ifmo.lesson3;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertRandomInRange {
    public void assertInRange(int[] nums, int start, int end) {
        for (int randomNumber : nums) {
            assertTrue(randomNumber >= start && randomNumber <= end, "" + randomNumber
                    + " is not in [" + start + ";" + end + "] range");
        }
    }
}
