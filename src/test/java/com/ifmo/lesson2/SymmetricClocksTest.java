package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymmetricClocksTest {

    @Test
    void symmetricTimes() {
        assertEquals(15, SymmetricClocks.symmetricTimes());
    }
}