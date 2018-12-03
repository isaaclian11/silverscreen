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
	/**
	 * Value that represents the specific column for each chat in the database.
	 */
	@Id
	@Column(name = "id")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int id;
	
	/**
	 * The message that a account/user is sending in the database.
	 */
	@Column(name = "Message")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String message;
	/**
	 * The account/user that is sending the message.
	 */
	@Column(name = "Sender")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String sender;
	
	/**
	 * The account/user who is receiving the message.
	 */
	@Column(name = "Recipient")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String recipient;
	
	/**
	 * Method that will get the message that was sent from the database.
	 * @return
	 */
	public String getMessages() {
		return message;
	}
	/**
	 * Method that will return which account/user sent a message.
	 * @return
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * Method that will return the account/username of the intended recipient of a message that 
	 * was stored on the database.
	 * @return
	 */
	public String getRecipient() {
		return recipient;
	}
	/**
	 * Method that will add the String message into the database.
	 * @param message
	 */
	public void addMessage(String message) {
		this.message = message;
	}
	/**
	 * Method that will add the user/account sender who is sending the message and put it in the database.
	 * @param sender
	 */
	public void addSender(String sender) {
		this.sender = sender;
	}
	/**
	 * This method will add who is the account/user who will receive the message into the database.
	 * @param recipient
	 */
	public void addRecipient(String recipient) {
		this.recipient = recipient;
	}
}