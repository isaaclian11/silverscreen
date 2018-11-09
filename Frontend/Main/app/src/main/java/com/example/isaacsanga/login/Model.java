package com.example.isaacsanga.login;

public class Model {
    int id;
    String name;
    String desc;
    String poster;
    String me;
    int score;

    public int getId(){
        return id;
    }

    public int getScore() {
        return score;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPoster() {
        return poster;
    }

    public String getMe() {
        return me;
    }

    public Model(int id, String name, String desc, String poster, String me, int score) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.poster = poster;
        this.me = me;
        this.score = score;
    }

}
