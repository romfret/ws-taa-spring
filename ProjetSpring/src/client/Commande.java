package client;

import server.ChatRoom;

public interface Commande {
	
	public void execute();
	public void setUser(User u);
	public void setTarget(Object trgt);
	
}
