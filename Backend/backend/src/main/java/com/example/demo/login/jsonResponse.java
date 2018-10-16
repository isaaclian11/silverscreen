package com.example.demo.login;

public class jsonResponse {

    private String s;
    public jsonResponse(String s){
        this.s = s;
    }

    public String getRespons(){
        return s;
    }

    public void getResponse(String response){
        this.s = response;
    }
}

