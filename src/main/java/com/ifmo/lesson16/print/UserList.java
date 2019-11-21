package com.ifmo.lesson16.print;

import java.io.*;
import java.util.List;
import java.util.Set;

public class UserList implements Externalizable {
    private Set<String> nameUsers;

    public Set<String> getNameUsers() {
        return nameUsers;
    }

    public void setNameUsers(Set<String> nameUsers) {
        this.nameUsers = nameUsers;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(nameUsers);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nameUsers = (Set<String>) in.readObject();
    }
}
