package client;

import java.rmi.RemoteException;

import server.ChatRoom;


public class CommandeServChatRoomUnsubscribe implements Commande {

	private ChatRoom target = null;
	private User user = null;

	public void execute() {
		try {
			target.unsubscribe(user.getPseudo());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setTarget(Object cr) {
		target = (ChatRoom) cr;
	}

//	public void setTarget(ChatRoom target) {
//		this.target = target;
//	}

}
