package server;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {
	
	@AfterReturning("execution(public * server.Authent.authentSuccess(..))") //authentification
	public void logSuccess(JoinPoint pjp) throws Throwable{
		System.out.println("###log - after: " + pjp.getSignature().getName());
		if (((Authent)pjp.getTarget()).isDebug()){
			System.out.println("\t\t[SampleLoginModule] "+ "authentication succeeded");
		}
	}

	@AfterReturning("execution(public * server.Authent.authentFail(..))") //authentification
	public void logFail(JoinPoint pjp) throws Throwable{
		System.out.println("###log - after: " + pjp.getSignature().getName());
		if (((Authent)pjp.getTarget()).isDebug()){
			System.out.println("\t\t[SampleLoginModule] "+ "authentication failed");
		}
	}
	
}
