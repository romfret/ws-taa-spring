package server;

import java.rmi.RemoteException;

import javax.security.auth.login.FailedLoginException;

public interface IAuthent {

    public boolean authentification(String username, String password) throws FailedLoginException, RemoteException;
	
}
