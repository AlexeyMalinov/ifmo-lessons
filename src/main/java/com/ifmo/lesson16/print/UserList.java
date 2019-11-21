package com.ifmo.lesson16.print;

import java.io.Serializable;
import java.util.List;

public class UserList implements Serializable {
    private List<String> nameUsers;

    public List<String> getNameUsers() {
        return nameUsers;
    }

    public void setNameUsers(List<String> nameUsers) {
        this.nameUsers = nameUsers;
    }

}
