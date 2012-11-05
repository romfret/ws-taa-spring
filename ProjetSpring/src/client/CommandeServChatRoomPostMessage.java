package client;

import java.rmi.RemoteException;

import javax.swing.JComponent;
import javax.swing.JTextField;

import server.ChatRoom;


public class CommandeServChatRoomPostMessage implements Commande {

	private ChatRoom target;
	private User user;
	private JComponent field;

	public void execute() {
		
		try {
			target.postMessage(user.getPseudo(), ((JTextField)field).getText());
		} catch (Exception exception) {
			System.err.println("Envoi message impossible :");
			exception.printStackTrace();
		}
		((JTextField)field).setText("");
		field.requestFocus();
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setField(JComponent f) {
		this.field = f;
	}

	@Override
	public void setTarget(Object cr) {
		target = (ChatRoom) cr;
	}

//	public void setTarget(ChatRoom target) {
//		this.target = target;
//	}	

}
