package com.habibfr.contact_management;

public class Contact {
    String id;
    String name;

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
