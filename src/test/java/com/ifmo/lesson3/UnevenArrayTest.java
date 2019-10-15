package com.ifmo.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnevenArrayTest {

    private static final String RESULT = """
                                        1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 51 53 55 57 59 61 63 65 67 69 71 73 75 77 79 81 83 85 87 89 91 93 95 97 99
                                        99 97 95 93 91 89 87 85 83 81 79 77 75 73 71 69 67 65 63 61 59 57 55 53 51 49 47 45 43 41 39 37 35 33 31 29 27 25 23 21 19 17 15 13 11 9 7 5 3 1
                                        """;

    @Test
    void main() {
        new CompareToStdoutLines().compare(() -> UnevenArray.main(new String[0]), RESULT);
    }

    @Test
    void unevenArray() {
        assertArrayEquals(uneven(), UnevenArray.unevenArray());
    }

    private int[] uneven() {
        int[] uneven = new int[50];

        for (int i = 0, j = 1; i < uneven.length; i++) {
            uneven[i] = j;

            j += 2;
        }

        return uneven;
    }
}