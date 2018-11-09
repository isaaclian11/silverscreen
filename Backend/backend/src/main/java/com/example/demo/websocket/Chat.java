package com.example.demo.websocket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table (name = "chat2")
public class Chat {
	@Id
	@Column(name = "id")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int id;
	
	@Column(name = "Message")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String message;
	
	@Column(name = "Sender")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String sender;
	
	@Column(name = "Recipient")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String recipient;
	
	public String getMessages() {
		return message;
	}
	public String getSender() {
		return sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void addMessage(String message) {
		this.message = message;
	}
	public void addSender(String sender) {
		this.sender = sender;
	}
	public void addRecipient(String recipient) {
		this.recipient = recipient;
	}
}