package com.ifmo.lesson3;

import com.ifmo.fun.StdoutGrabber;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertNonEmptyStdout {
    public void assertNonEmpty(Runnable run) {
        String res = new StdoutGrabber().grab(run, true);

        assertTrue(res.length() > 0, "Nothing is in output");
    }
}
