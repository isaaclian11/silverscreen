package com.example.demo.login;

import java.util.List;

public class jsonResponse {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String firstname;
    private String lastname;
    public jsonResponse(Login login, String result){
        this.firstname = login.getFirstname();
        this.lastname = login.getLastname();
        this.result = result;
    }
    public jsonResponse(String result){
        this.result = result;
    }

}

