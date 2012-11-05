package server;

import java.io.Serializable;

public class StrategyVerifPasswHeavyConcrete implements StrategyVerifPassw {

	@Override
	public boolean testPassword(String cs, String password) {
		boolean result = true;
		int i = 0;
		while (i < cs.toCharArray().length && result) {
			if (cs.toCharArray()[i] != password.toCharArray()[i])
				result = false;
			i++;
		}
		return result;
	}

}
