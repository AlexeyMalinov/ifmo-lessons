package com.ifmo.lesson2;

import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonNegativeSequenceTest {

    @Test
    void main() {
        String result = new StdoutGrabber()
                .grab(() -> NonNegativeSequence.main(new String[0]), true);

        assertEquals(sequence(), result);
    }

    private String sequence() {
        StringBuilder sb = new StringBuilder();

        for (int i = 90; i >= 0; i -= 5) {
            sb.append(i);
        }

        return sb.toString();
    }
}