package com.example.isaacsanga.login;

public class ChatModel {
    String myMessage;
    Boolean isMyMessage;

    public ChatModel(String myMessage, Boolean isMyMessage) {
        this.myMessage = myMessage;
        this.isMyMessage = isMyMessage;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(Boolean myMessage) {
        isMyMessage = myMessage;
    }

    public void setMyMessage(String myMessage) {
        this.myMessage = myMessage;
    }

}
