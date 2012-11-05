package client;

import java.rmi.RemoteException;

import javax.security.auth.login.FailedLoginException;

import server.IMainServer;

public class CommandeServAuthent implements Commande {

	private IMainServer target;
	private String usern, passw;
	private boolean resultAuth = false;
	
	@Override
	public void execute() {
		try {
			resultAuth = target.authentification(usern, passw);
		} catch (FailedLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setUser(User u) {
		
	}

	@Override
	public void setTarget(Object serv) {
		target = (IMainServer) serv;
	}

	public boolean getRes() {
		return resultAuth;
	}

	public void setUsern(String usern) {
		this.usern = usern;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

}
