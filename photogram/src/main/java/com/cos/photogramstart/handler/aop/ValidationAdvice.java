package com.cos.photogramstart.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //RestController, Service 이런 것들이 Component를 상속하여 만들어져있음
@Aspect
public class ValidationAdvice {
	
	//Before, After (public/private/protected 
	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// proceedingJoinPoint => apiController 함수의 모든 곳에 접근할 수 있는 변수
		// 함수보다 먼저 실행됨
		
		// 다시 그 함수로 돌아가서 실행
		return proceedingJoinPoint.proceed();
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice() {
		
	}
}
