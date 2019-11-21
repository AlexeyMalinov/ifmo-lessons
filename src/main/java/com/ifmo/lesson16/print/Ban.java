package com.ifmo.lesson16.print;

import java.io.Serializable;

public class Ban implements Serializable {
    private final String ipAddress;

    public Ban(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
