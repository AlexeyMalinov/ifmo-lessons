package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class IsEvenTest {

    @Test
    void isEven() {
        var rnd = new Random(0);

        for (int i = 0; i < 5; i++) {
            int n = rnd.nextInt(100);

            String even = IsEven.isEven(n).toLowerCase().strip();

            String expected = n % 2 == 0 ? "четное" : "нечетное";

            assertEquals(expected, even, "n = " + n);
        }
    }
}