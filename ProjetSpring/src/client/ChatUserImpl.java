package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import server.ChatRoom;
import server.IMainServer;

import com.sun.security.auth.callback.DialogCallbackHandler;


public class ChatUserImpl extends UnicastRemoteObject implements ChatUser, Runnable, User {

	private String pseudo;

	private String name;

	private ChatUI ui;

	private CommandeServChatRoomSubscribe cmdSubscr;

	public void setCmdSubscr(CommandeServChatRoomSubscribe cmdSubscr) {
		this.cmdSubscr = cmdSubscr;
	}

	public ChatUserImpl() throws RemoteException {
		super(); // Appel au constructeur de UnicastRemoteObject
//		try {
//			//this.serv = (IMainServer) Naming.lookup("rmi://localhost/MainServer");
//			//this.room = (ChatRoom) Naming.lookup("rmi://localhost/ChatRoom");
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			System.exit(0);
//		}

		this.createIHM();
		// this.requestPseudo();
	}

	public void createIHM() throws RemoteException {
	}

	public void displayMessage(String message) throws RemoteException {
		ui.displayMessage(message);
	}

//	public void run(){
//		try {
//			serv.probleme();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void run() {
		SampleLoginModule lc = null;
		try {
			try {
				LocateRegistry.createRegistry(1099);
			} catch (Exception e) {
				System.err.println("Il y a deja un registre de lance");
			}

			ApplicationContext beanFactory = new ClassPathXmlApplicationContext("server_configuration.xml");
			lc = (SampleLoginModule) beanFactory.getBean("idSmpLoginModule");
			//Set principals = new HashSet();
			//principals
			//		.add(new sample.principal.SamplePrincipal("testUser"));
			//Subject mySubject = new Subject(false, principals,
			//		new HashSet(), new HashSet());

			lc.initialize();
			//System.out.println("###lol");

		} catch (SecurityException se) {
			System.err.println("Cannot create LoginContext. "
					+ se.getMessage());
			System.exit(-1);
		}

		// the user has 3 attempts to authenticate successfully
		int i;
		for (i = 0; i < 3; i++) {
			try {

				// attempt authentication
				lc.login();

				// if we return with no exception, authentication succeeded
				break;

			} catch (LoginException le) {

				System.err.println("Authentication failed:");
				System.err.println("  " + le.getMessage());
				try {
					Thread.currentThread().sleep(3000);
				} catch (Exception e) {
					// ignore
				}

			}
		}

		// did they fail three times?
		if (i == 3) {
			System.out.println("Sorry");
			
			System.exit(-1);
		}

		System.out.println("Authentication succeeded!");
		this.pseudo = ui.requestPseudo();
		cmdSubscr.setPseudo(this.pseudo);
		cmdSubscr.execute();
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUi(ChatUI ui) {
		this.ui = ui;
	}

	public ChatUI getUi() {
		return ui;
	}

}