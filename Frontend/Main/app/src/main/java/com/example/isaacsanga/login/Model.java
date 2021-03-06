package com.example.isaacsanga.login;

public class Model {
    int id;
    String name;
    String desc;
    String poster;
    int parentID;
    int score;
    String title;

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

    public int parentID() {
        return parentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Model(int id, String title, String name, String desc, String poster, int parentID, int score) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.poster = poster;
        this.parentID = parentID;
        this.score = score;
        this.title = title;
    }

}
