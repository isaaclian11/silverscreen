package com.example.isaacsanga.login;

public class Model {
    String name;
    String desc;
    int id;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public Model(String name, String desc, int id) {
        this.name = name;
        this.desc = desc;
        this.id = id;
    }
}
