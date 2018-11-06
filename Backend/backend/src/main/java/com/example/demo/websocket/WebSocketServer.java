package com.example.demo.websocket;

import java.io.IOException;
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
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	private Session session;
	private static Map<Session, String> sessionUserMap = new HashMap();
	public static Map<String, Session> usernameSessionMap = new HashMap();
	private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		//*TODO* pull all of the history from the server
		//adds user to the sessions (one is of users and one is of usernames)
		logger.info("Entered into Open");
		sessionUserMap.put(session, username);
		usernameSessionMap.put(username, session);
		
		//tells the chat that the user with the specific username has joined the chatroom
		String message = "User " + username + " has joined the chat"; 
		broadcast(message);
	}
	
	public void onMessage(Session session, String message) throws IOException {
		logger.info("Entered into message: Got Message: " + message);
		//gets the username and checks if there is an @ in the front (this means that it is only suppose to go to one user)
		String username = sessionUserMap.get(session);
		if (message.startsWith("@")) {
			
			//this will figure out where the message needs to be sent and then send the message to the user who sent it and the one who is the intended recipent)
			String destUsername = message.split(" ")[0].substring(1);
			sendMessageToAPractiuclarUser(destUsername, "[DM] " + username + ": " + message);
			sendMessageToAPractiuclarUser(username, "[DM] " + username + ": " + message);
			//add the message to the chathistory (a text file?)
		}
		else {
			broadcast(username + ": " + message);
			//add the message to the chat history (a text file?)
		}

	}
	
	public void onClose(Session session) throws IOException {
		logger.info("Entered into close");
		String username = sessionUserMap.get(session);
		sessionUserMap.remove(session);
		usernameSessionMap.remove(username);
		String message = "user " + username + " has left the chat";
		broadcast(message);
		//upload the file to the server
		
	}
	public void onError (Session session, Throwable throwable) {
		logger.info("Entered into error");
	}
	
	public void sendMessageToAPractiuclarUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		}
		catch (IOException E){
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