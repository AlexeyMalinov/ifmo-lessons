package com.ifmo.lesson16.print;

import java.io.Serializable;

public class Ping implements Serializable {

    private final long timestamp;

    public Ping() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
