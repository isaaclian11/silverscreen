package com.example.demo.review;

import java.util.List;

public class jsonResponse {

    private List<Review> s;
    
    public jsonResponse(List<Review> s){
        this.s = s;
    }

    public List<Review> getResponse(){
        return s;
    }

    public void getResponse(List<Review> response){
        this.s = response;
    }
}

