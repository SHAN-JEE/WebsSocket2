package xyz.shanmugavel.poc;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WebSocket2 {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println(String.format("%s has opened a connection.", session.getId()));
		try {
			session.getBasicRemote().sendText("Connected successfully");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(String.format("Message [%s] from %s.", message, session.getId()));
		try {
			session.getBasicRemote().sendText(String.format("Server>> %s", message));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println(String.format("Session %s has ended.", session.getId()));
	}
}
