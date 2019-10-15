package com.ifmo.lesson2;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class First55Test {

    @Test
    void main() {
        String result = new StdoutGrabber()
                .grab(() -> First55.main(new String[0]), true);

        assertEquals(progression(), result);
    }

    private String progression() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0, j = 1; i < 55; i++, j += 2) {
            sb.append(j);
        }

        return sb.toString();
    }
}