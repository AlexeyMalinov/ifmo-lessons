package com.ifmo.lesson2;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    private static final String FIBONACCI = "1123581321345589144";

    @Test
    void main() {
        String fib = new StdoutGrabber().grab(() -> Fibonacci.main(new String[0]), true);

        assertEquals(FIBONACCI, fib);
    }
}