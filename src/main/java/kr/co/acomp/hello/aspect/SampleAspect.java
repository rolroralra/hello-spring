package kr.co.acomp.hello.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect 
public class SampleAspect {

	@Before("execution(* kr.co.acomp.hello..*.*(..))")
	public void before(JoinPoint joinPoint) {
		String targetMethodName = joinPoint.getSignature().getName();
		
		System.out.println(targetMethodName + " is invoked..");
	}
	
	
	@AfterThrowing(pointcut="execution(* kr.co.acomp.hello..*.*(..))",throwing="e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String targetMethodName = joinPoint.getSignature().getName();
		System.out.println(targetMethodName + " make error :: " + e.getMessage());
	}
}
