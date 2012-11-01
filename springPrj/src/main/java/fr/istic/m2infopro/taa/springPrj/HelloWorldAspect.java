package fr.istic.m2infopro.taa.springPrj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class HelloWorldAspect {
	
	// "execution(public String fr.istic.m2infopro.taa.springPrj.HelloWorld.getString(..))"
	
	@Before("execution(* fr.istic.m2infopro.taa.springPrj.HelloWorld.printHello(..))") // Execute avant l'execution printHello(..)
	public void logBefore(JoinPoint joinPoint) {
 
		System.out.println("Je t'aspect sale tarlouse !!!");
	}
}
