package com.ifmo.lesson16.print;

import java.io.*;

public class Ping implements Externalizable {

    private long timestamp;

    public Ping() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(timestamp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        timestamp = in.readLong();
    }
}
