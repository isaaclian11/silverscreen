package com.example.demo.review;

import java.util.List;

public class jsonResponse 
{
    private List<ReviewAndName> s;
    
    public jsonResponse(List<ReviewAndName> s)
    {
        this.s = s;
    }
    
    public List<ReviewAndName> getResponse()
    {
        return s;
    }

    public void getResponse(List<ReviewAndName> response)
    {
        this.s = response;
    }
}

