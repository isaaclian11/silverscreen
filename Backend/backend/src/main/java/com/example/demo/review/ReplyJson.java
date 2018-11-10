package com.example.demo.review;

import java.util.List;

public class ReplyJson {
    private List<ReviewReplies> s;

    public ReplyJson(List<ReviewReplies> s)
    {
        this.s = s;
    }

    public List<ReviewReplies> getResponse()
    {
        return s;
    }

    public void getResponse(List<ReviewReplies> response)
    {
        this.s = response;
    }
}
