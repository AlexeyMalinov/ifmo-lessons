package com.ifmo.fun;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertRandomInterval {
    private final int start;
    private final int end;
    private final Supplier<Integer> generator;

    public AssertRandomInterval(int start, int end, Supplier<Integer> generator) {
        this.start = start;
        this.end = end;
        this.generator = generator;
    }

    public void assertInterval() {
        Set<Integer> randoms = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            randoms.add(i);

            int randomInt = generator.get();

            assertTrue(randomInt >= start, "Out of interval randomInt = " + randomInt);
            assertTrue(randomInt <= end, "Out of interval randomInt = " + randomInt);
        }

        assertTrue(randoms.size() > 50, "Of 1000 randoms, only " +
                randoms.size() + " unique integers generated");
    }
}
