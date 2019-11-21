package com.ifmo.lesson16.print;

import java.io.*;
import java.util.Date;

public class ServerTime implements Externalizable {
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return new Date(timeStamp).toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(timeStamp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        timeStamp = in.readLong();
    }
}
