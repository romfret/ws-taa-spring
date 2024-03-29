/*
 * @(#)SampleLoginModule.java	1.18 00/01/11
 *
 * Copyright 2000-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY 
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR 
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR 
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE 
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */

package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import server.ChatRoom;
import server.IAuthent;


/**
 * <p>
 * This sample LoginModule authenticates users with a password.
 * 
 * <p>
 * This LoginModule only recognizes one user: testUser
 * <p>
 * testUser's password is: testPassword
 * 
 * <p>
 * If testUser successfully authenticates itself, a <code>SamplePrincipal</code>
 * with the testUser's user name is added to the Subject.
 * 
 * <p>
 * This LoginModule recognizes the debug option. If set to true in the login
 * Configuration, debug messages will be output to the output stream,
 * System.out.
 * 
 * @version 1.18, 01/11/00
 */
public class SampleLoginModule implements LoginModule {

	// initial state
	private Subject subject;

	private CallbackHandler callbackHandler;

	private Map sharedState;

	private Map options;

	// configurable option
	private boolean debug;

	// the authentication status
	private boolean succeeded;

	private boolean commitSucceeded;

	// username and password
	private String username;

	private String password;

	// testUser's SamplePrincipal
	private SamplePrincipal userPrincipal;

	private CommandeServAuthent cmdAuthent;

	public void setCmdAuthent(CommandeServAuthent cmdAuthent) {
		this.cmdAuthent = cmdAuthent;
	}

	public SampleLoginModule() {
	}

	/**
	 * Initialize this <code>LoginModule</code>.
	 * 
	 * <p>
	 * 
	 * @param subject
	 *            the <code>Subject</code> to be authenticated.
	 *            <p>
	 * 
	 * @param callbackHandler
	 *            a <code>CallbackHandler</code> for communicating with the
	 *            end user (prompting for user names and passwords, for
	 *            example).
	 *            <p>
	 * 
	 * @param sharedState
	 *            shared <code>LoginModule</code> state.
	 *            <p>
	 * 
	 * @param options
	 *            options specified in the login <code>Configuration</code>
	 *            for this particular <code>LoginModule</code>.
	 */
	public void initialize() {
		// initialize any configured options
		debug = "true".equalsIgnoreCase((String) options.get("debug"));
	}

	/**
	 * Authenticate the user by prompting for a user name and password.
	 * 
	 * <p>
	 * 
	 * @return true in all cases since this <code>LoginModule</code> should
	 *         not be ignored.
	 * 
	 * @exception FailedLoginException
	 *                if the authentication fails.
	 *                <p>
	 * 
	 * @exception LoginException
	 *                if this <code>LoginModule</code> is unable to perform
	 *                the authentication.
	 */
	public boolean login() throws LoginException {

		// prompt for a user name and password
		if (callbackHandler == null)
			throw new LoginException("Error: no CallbackHandler available "
					+ "to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("user name: ");
		callbacks[1] = new PasswordCallback("password: ", false);

		try {
			callbackHandler.handle(callbacks);
			username = ((NameCallback) callbacks[0]).getName();
			String tmpPassword = String.copyValueOf(((PasswordCallback) callbacks[1]).getPassword());
			if (tmpPassword == null) {
				// treat a NULL password as an empty password
				tmpPassword = "";
			}
			password = tmpPassword;
			//System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length());
			((PasswordCallback) callbacks[1]).clearPassword();

		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException("Error: " + uce.getCallback().toString()
					+ " not available to garner authentication information "
					+ "from the user");
		}

		// print debugging information
		if (debug) {
			System.out.println("\t\t[SampleLoginModule] "
					+ "user entered user name: " + username);
			System.out.print("\t\t[SampleLoginModule] "
					+ "user entered password: ");
			for (int i = 0; i < password.length(); i++)
				System.out.print(password.toCharArray()[i]);
			System.out.println();
		}

		cmdAuthent.setUsern(username);
		cmdAuthent.setPassw(password);
		cmdAuthent.execute();
		return cmdAuthent.getRes();

	}

	/**
	 * <p>
	 * This method is called if the LoginContext's overall authentication
	 * succeeded (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL
	 * LoginModules succeeded).
	 * 
	 * <p>
	 * If this LoginModule's own authentication attempt succeeded (checked by
	 * retrieving the private state saved by the <code>login</code> method),
	 * then this method associates a <code>SamplePrincipal</code> with the
	 * <code>Subject</code> located in the <code>LoginModule</code>. If
	 * this LoginModule's own authentication attempted failed, then this method
	 * removes any state that was originally saved.
	 * 
	 * <p>
	 * 
	 * @exception LoginException
	 *                if the commit fails.
	 * 
	 * @return true if this LoginModule's own login and commit attempts
	 *         succeeded, or false otherwise.
	 */
	public boolean commit() throws LoginException {
		if (succeeded == false) {
			return false;
		} else {
			// add a Principal (authenticated identity)
			// to the Subject

			// assume the user we authenticated is the SamplePrincipal
			try {
				LocateRegistry.createRegistry(1099);
			} catch (Exception e) {
				System.err.println("Il y a deja un registre de lance");
			}

			ApplicationContext beanFactory = new ClassPathXmlApplicationContext("server_configuration.xml");
			userPrincipal = (SamplePrincipal) beanFactory.getBean("idSamplePrincipal");
			userPrincipal.setName(username);
			
			if (!subject.getPrincipals().contains(userPrincipal))
				subject.getPrincipals().add(userPrincipal);

			if (debug) {
				System.out.println("\t\t[SampleLoginModule] "
						+ "added SamplePrincipal to Subject");
			}

			// in any case, clean out state
			username = null;
			for (int i = 0; i < password.length(); i++)
				password.toCharArray()[i] = ' ';
			password = null;

			commitSucceeded = true;
			return true;
		}
	}

	/**
	 * <p>
	 * This method is called if the LoginContext's overall authentication
	 * failed. (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL
	 * LoginModules did not succeed).
	 * 
	 * <p>
	 * If this LoginModule's own authentication attempt succeeded (checked by
	 * retrieving the private state saved by the <code>login</code> and
	 * <code>commit</code> methods), then this method cleans up any state that
	 * was originally saved.
	 * 
	 * <p>
	 * 
	 * @exception LoginException
	 *                if the abort fails.
	 * 
	 * @return false if this LoginModule's own login and/or commit attempts
	 *         failed, and true otherwise.
	 */
	public boolean abort() throws LoginException {
		if (succeeded == false) {
			return false;
		} else if (succeeded == true && commitSucceeded == false) {
			// login succeeded but overall authentication failed
			succeeded = false;
			username = null;
			if (password != null) {
				for (int i = 0; i < password.length(); i++)
					password.toCharArray()[i] = ' ';
				password = null;
			}
			userPrincipal = null;
		} else {
			// overall authentication succeeded and commit succeeded,
			// but someone else's commit failed
			logout();
		}
		return true;
	}

	/**
	 * Logout the user.
	 * 
	 * <p>
	 * This method removes the <code>SamplePrincipal</code> that was added by
	 * the <code>commit</code> method.
	 * 
	 * <p>
	 * 
	 * @exception LoginException
	 *                if the logout fails.
	 * 
	 * @return true in all cases since this <code>LoginModule</code> should
	 *         not be ignored.
	 */
	public boolean logout() throws LoginException {

		subject.getPrincipals().remove(userPrincipal);
		succeeded = false;
		succeeded = commitSucceeded;
		username = null;
		if (password != null) {
			for (int i = 0; i < password.length(); i++)
				password.toCharArray()[i] = ' ';
			password = null;
		}
		userPrincipal = null;
		return true;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setCallbackHandler(CallbackHandler callbackHandler) {
		this.callbackHandler = callbackHandler;
	}

	public void setOptions(Map options) {
		this.options = options;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	public void setCommitSucceeded(boolean commitSucceeded) {
		this.commitSucceeded = commitSucceeded;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserPrincipal(SamplePrincipal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		System.out.println("JAMAIS APPELLE");
		
	}
}
