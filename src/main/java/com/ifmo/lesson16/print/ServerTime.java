package com.ifmo.lesson16.print;

import java.io.Serializable;
import java.util.Date;

public class ServerTime implements Serializable {
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
}
