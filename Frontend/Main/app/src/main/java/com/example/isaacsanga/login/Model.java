package com.example.isaacsanga.login;

public class Model {
    String name;
    String desc;
    String poster;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPoster(){return poster;}

    public Model(String name, String desc, String poster) {
        this.name = name;
        this.desc = desc;
        this.poster = poster;
    }
}
