package com.example.demo.websocket;

public class jsonResponse {
	 private String result;

	    public String getResult() {
	        return result;
	    }

	    public void setResult(String result) {
	        this.result = result;
	    }

	    public String getFirstname() {
	        return sender;
	    }

	    public void setFirstname(String firstname) {
	        this.sender = sender;
	    }

	    public String getrecipient() {
	        return recipient;
	    }

	    public void setrecipient(String recipient) {
	        this.recipient = recipient;
	    }

	    private String sender;
	    private String recipient;
	    public jsonResponse(Chat chat, String result){
	        this.sender = chat.getSender();
	        this.recipient = chat.getRecipient();
	        this.result = result;
	    }
	    public jsonResponse(String result){
	        this.result = result;
	    }
}
