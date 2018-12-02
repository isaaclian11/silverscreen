package com.example.demo.login;

import java.util.List;

public class myFriendsJSON {
    List<myFriends> result;

    public myFriendsJSON(List<myFriends> result) {
        this.result = result;
    }

    public List<myFriends> getResult() {
        return result;
    }

    public void setResult(List<myFriends> result) {
        this.result = result;
    }
}
