package com.ifmo.lesson16.print;

import java.io.Serializable;

public class UnBan implements Serializable {

    private final String ipAddress;

    public UnBan(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
