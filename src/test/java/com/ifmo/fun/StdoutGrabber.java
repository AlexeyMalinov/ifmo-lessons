package com.ifmo.fun;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StdoutGrabber {
    public String grab(Runnable writeToStdout, boolean join) {
        var curOut = System.out;
        var bArr = new ByteArrayOutputStream();
        var ps = new PrintStream(bArr);

        System.setOut(ps);

        try {
            writeToStdout.run();
        } finally {
            System.setOut(curOut);
        }

        String res = new String(bArr.toByteArray());

        return join
                ? res.replace("\n", "")
                .replace(" ", "")
                .replace(",", "")
                : res;
    }
}
