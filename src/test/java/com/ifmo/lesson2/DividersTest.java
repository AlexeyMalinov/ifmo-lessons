package com.ifmo.lesson2;

import com.ifmo.fun.StdinWriter;
import com.ifmo.fun.StdoutGrabber;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DividersTest {

    @Test
    void main() {
        StdinWriter stdinWriter = new StdinWriter();
        StdoutGrabber stdoutGrabber = new StdoutGrabber();

        var rnd = new Random(0);

        for (int i = 0; i < 20; i++) {
            int n = rnd.nextInt(100);

            String result = stdoutGrabber
                    .grab(() -> stdinWriter.writeString("" + n + "\n",
                            () -> Dividers.main(new String[0])), true);

            assertEquals(expected(n), result, "n = " + n);
        }
    }

    private String expected(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (n % i == 0)
                sb.append(i);
        }

        return sb.toString();
    }
}