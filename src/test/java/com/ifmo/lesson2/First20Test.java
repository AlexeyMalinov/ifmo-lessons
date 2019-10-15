package com.ifmo.lesson2;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class First20Test {

    @Test
    void main() {
        String result = new StdoutGrabber()
                .grab(() -> First20.main(new String[0]), true);

        assertEquals(sequence(), result);
    }

    private String sequence() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0, j = 1; i < 20; i++, j *= 2) {
            sb.append(j);
        }

        return sb.toString();
    }
}