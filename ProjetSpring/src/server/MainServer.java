package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import javax.security.auth.login.FailedLoginException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import client.ChatUser;


public class MainServer extends UnicastRemoteObject implements IMainServer {

	private ChatRoom theChatRoom;
	private IAuthent theAuthent;
	
	public MainServer() throws RemoteException {
	}


	public static void main(String[] args) throws Exception {
		try {
			LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			System.err.println("Il y a deja un registre de lance");
		}

		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("server_configuration.xml");
		MainServer serv = (MainServer) beanFactory.getBean("server");
        
		Naming.rebind("MainServer", serv);
		//TODO question
	}

	public void probleme(){
		try {
			theAuthent.authentification("toto", "passtoto");
		} catch (FailedLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see server.IMainServer#getTheChatRoom()
	 */
//	@Override
//	public ChatRoom getTheChatRoom() {
//		return theChatRoom;
//	}


	/* (non-Javadoc)
	 * @see server.IMainServer#getTheAuthent()
	 */
//	@Override
//	public IAuthent getTheAuthent() {
//		return theAuthent;
//	}
	
	@Override
	public boolean authentification(String user, String passw) throws FailedLoginException, RemoteException{
		return theAuthent.authentification(user, passw);
	}
	
	@Override
	public void subscribe(ChatUser user, String pseudo) throws RemoteException {
		theChatRoom.subscribe(user, pseudo);
	}

	@Override
	public void unsubscribe(String pseudo) throws RemoteException {
		theChatRoom.unsubscribe(pseudo);
	}

	@Override
	public void postMessage(String pseudo, String message) throws RemoteException {
		theChatRoom.postMessage(pseudo, message);
	}

	public void setTheChatRoom(ChatRoom theChatRoom) {
		this.theChatRoom = theChatRoom;
	}


	public void setTheAuthent(IAuthent theAuthent) {
		this.theAuthent = theAuthent;
	}
	
}