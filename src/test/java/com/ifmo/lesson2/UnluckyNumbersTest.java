package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnluckyNumbersTest {

    @Test
    void unluckyNumbersCount() {
        assertEquals(43840, UnluckyNumbers.unluckyNumbersCount());
    }
}