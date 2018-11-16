package com.example.demo.websocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antlr.collections.List;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	@Autowired
	ChatRepository chatRepository;
	private Session session;
	private static Map<Session, String> sessionUserMap = new HashMap();
	public static Map<String, Session> usernameSessionMap = new HashMap();
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	protected ArrayList<String> chatHistory = new ArrayList<String>();
	private String sender;
	private String recipient;
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		//keeps track of the name of user (this is important when pushing the message to the server)
		//adds user to the sessions (one is of users and one is of usernames)
		logger.info("Entered into Open");
		sessionUserMap.put(session, username);
		usernameSessionMap.put(username, session);
		
		//tells the chat that the user with the specific username has joined the chatroom
		String message = "User " + username + " has joined the chat"; 
		broadcast(message);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		logger.info("Entered into message: Got Message: " + message);
		//gets the username and checks if there is an @ in the front (this means that it is only suppose to go to one user)
		String username = sessionUserMap.get(session);
		if (message.startsWith("@")) {
			
			//this will figure out where the message needs to be sent and then send the message to the user who sent it and the one who is the intended recipent)
			String destUsername = message.split(" ")[0].substring(1);
			int x = message.indexOf(" ");
			message = message.substring(x, message.length());
			sendMessageToAPractiuclarUser(destUsername, "[DM] " + username + ": " + message);
			sendMessageToAPractiuclarUser(username, "[DM] " + username + ": " + message);
			recipient = destUsername;
		}
		else {
			//since this message is not directed at only one person, the message can be broadcast to everyone
			broadcast(username + ": " + message);
			recipient = "everyone";
		}
		
	}
	
	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into close");
		//finds the user and removes them from the session
		String username = sessionUserMap.get(session);
		sessionUserMap.remove(session);
		usernameSessionMap.remove(username);
		//tells the chat the user left the chatroom
		String message = "user " + username + " has left the chat";
		broadcast(message);
	}
	@OnError
	public void onError (Session session, Throwable throwable) {
		logger.info("Entered into error");
	}
	
	public void sendMessageToAPractiuclarUser(String username, String message) {
		try {
			//attempts to send the String message to the user that has been passed through
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		}
		catch (IOException E){
			//if for some reason it cannot, it will print an error message to the user.
			logger.info("Exception " +E.getMessage().toString());
			E.printStackTrace();
		}
	}
	
	public static void broadcast(String message) throws IOException {
		sessionUserMap.forEach((session, username) -> {
			synchronized(session){
				try {
					session.getBasicRemote().sendText(message);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}