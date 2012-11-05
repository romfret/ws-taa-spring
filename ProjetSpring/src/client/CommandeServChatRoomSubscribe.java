package client;

import java.rmi.RemoteException;

import server.ChatRoom;
import server.IMainServer;

public class CommandeServChatRoomSubscribe implements Commande {

	private ChatRoom target;
	private String pseudo;
	private ChatUser user;
	
	@Override
	public void execute() {
		try {
			target.subscribe(user, pseudo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setUser(User u) {
		user = (ChatUser) u;
	}

	@Override
	public void setTarget(Object trgt) {
		target = (ChatRoom) trgt;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

}
