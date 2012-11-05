package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.security.auth.login.FailedLoginException;

import client.ChatUser;

public interface IMainServer extends Remote {

	//public abstract ChatRoom getTheChatRoom()throws RemoteException;

	//public abstract IAuthent getTheAuthent()throws RemoteException;
	
	public boolean authentification(String user, String passw) throws FailedLoginException, RemoteException ;
	
	public void subscribe(ChatUser user, String pseudo) throws RemoteException;
	
    public void unsubscribe(String pseudo) throws RemoteException;
    
	public void postMessage(String pseudo, String message) throws RemoteException;
	
	//public void probleme()throws RemoteException;

}