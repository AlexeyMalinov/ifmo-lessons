package com.ifmo.lesson3;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Fibonacci20Test {

    @Test
    void main() {
        String res = new StdoutGrabber().grab(() -> Fibonacci20.main(new String[0]), true);
        String expected = "11235813213455891442333776109871597258441816765";

        assertEquals(expected, res);
    }

    @Test
    void fibonacciNumbers() {
        int[] expected = new int[]{1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765};
        int[] fibonacciNumbers = Fibonacci20.fibonacciNumbers();

        assertArrayEquals(expected, fibonacciNumbers);
    }
}