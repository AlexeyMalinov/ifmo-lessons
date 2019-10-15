package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EvenArrayTest {
    private static final String RESULT = """
                                        2 4 6 8 10 12 14 16 18 20
                                        2
                                        4
                                        6
                                        8
                                        10
                                        12
                                        14
                                        16
                                        18
                                        20
                                         """;

    @Test
    void evenArray() {
        var expected = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20};

        assertArrayEquals(expected, EvenArray.evenArray());
    }

    @Test
    void main() {
        new CompareToStdoutLines().compare(() -> EvenArray.main(new String[0]), RESULT);
    }
}