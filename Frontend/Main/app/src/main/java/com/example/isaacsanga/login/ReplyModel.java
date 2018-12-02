package com.example.isaacsanga.login;

public class ReplyModel {
    String review, name;

    public ReplyModel(String review, String name) {
        this.review = review;
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
