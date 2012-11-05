package helpSpring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class LogAspect {
	
	@Before("execution(public * business.Story.get*(..))")
	public void logBefore(JoinPoint pjp) throws Throwable{
		System.out.println("###before: " + pjp.getSignature().getName() );//+pjp.getThis().toString());
	}
	
	@AfterReturning("execution(* *.*(..))")
	public void log(JoinPoint pjp) throws Throwable{
		System.out.println("###afterlol: " /*+ pjp.getSignature().getName()*/ +pjp.getTarget().getClass().getName());//+pjp.getThis().toString());
	}

}
