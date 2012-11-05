package server;

import java.rmi.RemoteException;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;

public class Authent implements IAuthent {

	private Map<String, String> alloweduser;
	private StrategyVerifPassw laStrat;
	
	private boolean debug;
	
	public boolean authentification(String username, String password)
			throws FailedLoginException {
		// verify the username/password
		boolean usernameCorrect = false;
		boolean passwordCorrect = false;
		if (this.alloweduser.containsKey(username)) {
			usernameCorrect = true;
			if (password.length() == this.alloweduser.get(username).length()
					&& laStrat.testPassword(this.alloweduser.get(username), password)) {

				// authentication succeeded!!!
				passwordCorrect = true;
				if(debug) LogHelper.debug(this, true);
				return true;
			}

		}

		// authentication failed -- clean out state
		if(debug) LogHelper.debug(this, false);
		
		for (int i = 0; i < password.length(); i++)
			password.toCharArray()[i] = ' ';
		if (!usernameCorrect) {
			throw new FailedLoginException("User Name Incorrect");
		} else {
			throw new FailedLoginException("Password Incorrect");

		}
	}

	public void authentSuccess() {
	}
	
	public void authentFail() {
	}

	public Map<String, String> getAlloweduser() {
		return alloweduser;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setAlloweduser(Map<String, String> alloweduser) {
		this.alloweduser = alloweduser;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public StrategyVerifPassw getLaStrat() {
		return laStrat;
	}

	public void setLaStrat(StrategyVerifPassw laStrat) {
		this.laStrat = laStrat;
	}
	
}
