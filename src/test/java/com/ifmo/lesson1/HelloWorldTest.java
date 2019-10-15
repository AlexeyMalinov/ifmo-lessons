package com.ifmo.lesson1;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloWorldTest {
    @Test
    void shouldPrintHelloWorld() {
        String result = new StdoutGrabber().grab(() -> HelloWorld.main(new String[0]), false);

        assertTrue(result.length() > 0, "Must be printed at least one symbol, but printed: " + result.length());
    }
}
