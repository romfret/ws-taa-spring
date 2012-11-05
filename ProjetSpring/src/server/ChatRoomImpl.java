package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import client.ChatUser;


public class ChatRoomImpl extends UnicastRemoteObject implements ChatRoom {
	
	private Map<String, ChatUser> users;	

	public ChatRoomImpl() throws RemoteException {
		super();
	}

	public void subscribe(ChatUser user, String pseudo) throws RemoteException {
		String message = "Connexion de " + pseudo;
		this.postMessage("ChatRoom", message);
		System.out.println(message);
		this.users.put(pseudo, user);
	}

	public void unsubscribe(String pseudo) throws RemoteException {
		String message = "Deconnexion de " + pseudo;
		System.out.println(message);
		this.users.remove(pseudo);
		this.postMessage("ChatRoom", message);
	}

	public void postMessage(String pseudo, String message)
			throws RemoteException {
		String fullMessage = pseudo + " >> " + message;
		System.out.println(fullMessage);

		for (ChatUser user : users.values()) {
			user.displayMessage(fullMessage);
		}
	}

	public Map<String, ChatUser> getUsers() {
		return users;
	}

	public void setUsers(Map<String, ChatUser> users) {
		this.users = users;
	}

}