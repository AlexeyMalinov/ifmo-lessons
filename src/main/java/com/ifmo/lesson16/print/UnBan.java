package com.ifmo.lesson16.print;

import java.io.*;

public class UnBan implements Externalizable {

    private String ipAddress;

    public UnBan(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(ipAddress);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ipAddress = (String) in.readObject();
    }
}
