package com.ifmo.lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LuckyTicketsTest {

    @Test
    void luckyTickets() {
        int luckyTickets = LuckyTickets.luckyTickets();

        assertEquals(55251, luckyTickets);
    }
}