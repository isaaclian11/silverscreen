package com.example.isaacsanga.login;

public class Model {
    String name;
    String desc;
    String poster;
    String me;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    boolean like;
    int score;

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

    public Model(String name, String desc, String poster, String me, int score) {
        this.name = name;
        this.desc = desc;
        this.poster = poster;
        this.me = me;
    }

}
