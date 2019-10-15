package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberTest {
    private static final Set<Integer> PRIMES = Set.of(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137,
            139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199);


    @Test
    void isPrime() {
        for (int i = 1; i <= 200; i++) {
            boolean prime = PrimeNumber.isPrime(i);

            assertEquals(PRIMES.contains(i), prime, "n = " + i);
        }
    }
}