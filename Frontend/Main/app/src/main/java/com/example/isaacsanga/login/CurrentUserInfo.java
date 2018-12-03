package com.example.isaacsanga.login;

import android.app.Application;

public class CurrentUserInfo extends Application {

    //Information of the user that is currently logged in
    private String username, admin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
