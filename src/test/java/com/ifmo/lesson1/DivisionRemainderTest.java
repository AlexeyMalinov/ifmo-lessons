package com.ifmo.lesson1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class DivisionRemainderTest {

    @Test
    void calculate() {
        var rnd = new Random(0);

        for (int i = 0; i < 5; i++) {
            var curOut = System.out;
            var bArr = new ByteArrayOutputStream();
            var ps = new PrintStream(bArr);

            System.setOut(ps);

            int q = rnd.nextInt(100);
            int w = rnd.nextInt(50);

            DivisionRemainder.calculate(q, w);

            System.setOut(curOut);

            var res = new String(bArr.toByteArray());

            var expected = String.format("%s / %s = %s и %s в остатке\n", q, w, q / w, q % w);

            assertEquals(expected, res);
        }
    }
}