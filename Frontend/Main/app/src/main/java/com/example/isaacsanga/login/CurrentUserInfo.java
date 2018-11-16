package com.example.isaacsanga.login;

import android.app.Application;

public class CurrentUserInfo extends Application {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
