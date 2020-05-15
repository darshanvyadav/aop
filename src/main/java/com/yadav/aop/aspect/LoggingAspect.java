package com.yadav.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = Logger.getLogger(LoggingAspect.class);
	
	// where ever we used this annotation control will come to this advice.
	@Before("@annotation(com.yadav.aop.annotation.LogInput)")
	public void before(JoinPoint jp) {
		logger.info("logging Input" + Arrays.toString(jp.getArgs()));
	}
	
	// use afterReturning if we want to use output object
	@AfterReturning(value = "@annotation(com.yadav.aop.annotation.LogOutput)", returning = "resultObject")
	public void after(JoinPoint joinPoint, Object resultObject) {
		logger.info("Logging output: " + resultObject.toString());
	}
	
	/**
	 * this method will return point cut object in to container
	 * point cut pattern "execution(return type _  base package . package . class . method ( arguments )  )"
	 * value  = "execution(* *.*.getPassenger(..))" - even this is valid expression 
	 * value = "execution(* *.getPassenger(..))" - even this is valid expression
	 *  return type followed by space followed by * followed by (arguments) 
	 *  
	 */
	@Pointcut(value = "execution(* com.yadav.aop.*.*.getPassenger(..))")
	public void getPointCut() {
	}
	
	@Around(value = "getPointCut()")
	// Value can be an annotation.
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper(); // used to convert object in to json string
		String methodName = pjp.getSignature().getName();
		String ClassName = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		logger.info("Method Invoked : "+ methodName + " ClassName :" + ClassName+ " Arguments" + Arrays.toString(array));
		
		// bellow line gives the output of the method.
		Object proceedObject = pjp.proceed();
		
		logger.info( " Output :"  + mapper.writeValueAsString(proceedObject));
		return proceedObject; 
		
	}
	
	
}
