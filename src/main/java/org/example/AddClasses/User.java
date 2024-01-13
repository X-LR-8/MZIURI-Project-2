package org.example.AddClasses;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private String inbox;

    public User(String name, String password, String inbox) {
        this.name = name;
        this.password = password;
        this.inbox = inbox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInbox() {
        return inbox;
    }

    public void setInbox(String inbox) {
        this.inbox = inbox;
    }
}
