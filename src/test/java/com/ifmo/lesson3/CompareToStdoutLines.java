package com.ifmo.lesson3;

import com.ifmo.fun.StdoutGrabber;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompareToStdoutLines {
    public void compare(Runnable run, String expected) {
        var grabber = new StdoutGrabber();

        String res = grabber.grab(run, false);

        List<String> lines = res.lines().map(String::strip).collect(Collectors.toUnmodifiableList());
        List<String> expectedLines = expected.lines().collect(Collectors.toUnmodifiableList());

        assertEquals(expectedLines, lines);
    }
}
