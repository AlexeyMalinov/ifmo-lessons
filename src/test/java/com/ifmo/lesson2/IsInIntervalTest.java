package com.ifmo.lesson2;

import com.ifmo.fun.AssertRandomInterval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsInIntervalTest {

    @Test
    void randomInt() {
        new AssertRandomInterval(5, 155, IsInInterval::randomInt)
            .assertInterval();
    }

    @Test
    void isInInterval() {
        int[] vals = new int[]{5, 25, 26, 90, 99, 100};
        boolean[] results = new boolean[]{false, false, true, true, true, false};

        for (int i = 0; i < vals.length; i++) {
            String inInterval = IsInInterval.isInInterval(vals[i]);

            if (results[i])
                assertEquals("Число " + vals[i] + " содержится в интервале (25,100)", inInterval);
            else
                assertEquals("Число " + vals[i] + " не содержится в интервале (25,100)", inInterval);
        }
    }
}