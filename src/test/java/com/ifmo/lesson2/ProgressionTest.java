package com.ifmo.lesson2;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ProgressionTest {

    @Test
    void main() {
        String result = new StdoutGrabber()
                .grab(() -> Progression.main(new String[0]), true);

        assertEquals(expected(), result);
    }

    private String expected() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1000; i < 10_000; i += 3) {
            sb.append(i);
        }

        return sb.toString();
    }
}