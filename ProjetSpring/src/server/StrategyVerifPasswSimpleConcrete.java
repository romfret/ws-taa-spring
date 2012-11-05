package server;

import java.io.Serializable;

public class StrategyVerifPasswSimpleConcrete implements StrategyVerifPassw {

	@Override
	public boolean testPassword(String str1, String str2) {
		return str1.equals(str2);
	}

}
