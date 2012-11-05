package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import server.MainServer;

public class Main {
	public static void main(String[] args) throws RemoteException {
		
		
		try {
			LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			System.err.println("Il y a deja un registre de lance");
		}

		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("server_configuration.xml");
		ChatUserImpl theCU = (ChatUserImpl) beanFactory.getBean("realChatUser");
		theCU.getUi().init();
		new Thread(theCU).start();
	}

}
