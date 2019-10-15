package com.ifmo.fun;

import java.io.ByteArrayInputStream;

public class StdinWriter {
    public void writeString(String str, Runnable run) {
        var curIn = System.in;

        var baIn = new ByteArrayInputStream(str.getBytes());

        System.setIn(baIn);

        try {
            run.run();
        } finally {
            System.setIn(curIn);
        }
    }
}
