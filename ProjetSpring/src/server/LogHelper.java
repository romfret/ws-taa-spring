package server;

public class LogHelper {

	public static void debug(Authent a, boolean b){
		if (b) a.authentSuccess();
		else a.authentFail();
	}
	
}
