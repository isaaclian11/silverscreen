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


public class WebSocketServer {
	private Session session;
	private static Set<WebSocketServer> chatEndPoints = new CopyOnWriteArraySet<>();
	private static Map<String, String> users = new HashMap();
	/*
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException() {
		this.session = session;
		chatEndPoints.add(this);
		users.put(session.getId(), username);
		//String message = "User " + username + " has joined the chat" 
		//broadcast(message);
	}
	public void onMessage(Session session, String message) throws IOException() {
		sendMessagetoAPraticularUser(session,echo); //change this to a speciifc person?
		//create method that will store the messgaes
	}
	public void onClose() {
		chatEndPoints.remove(this);
		String message = "user has left the chat";
		broadcast(message);
		
	}
	public void sendMessageToAPractiuclarUser(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message);
		}
		catch (IOException E){
			E.printStackTrace();
		}
	}
	public static void broadcast(String message) {
		throws IOException {
			chatEndPoints.forEach(endpoint ->){
				synchronized(endpoint){
					try {
						endpoint.session.getBasicRemote().sendText(message);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		
	}
	*/
}