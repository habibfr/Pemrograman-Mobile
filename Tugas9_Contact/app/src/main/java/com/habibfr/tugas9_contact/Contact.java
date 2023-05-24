package com.habibfr.tugas9_contact;

import java.io.Serializable;

public class Contact implements Serializable {
    int id;
    String name;
    String noHp;

    public Contact() {
    }

    public Contact(int id, String name, String noHp) {
        this.id = id;
        this.name = name;
        this.noHp = noHp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNoHp() {
        return noHp;
    }
}
