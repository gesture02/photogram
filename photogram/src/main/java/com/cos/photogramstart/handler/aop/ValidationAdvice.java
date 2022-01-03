package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

@Component //RestController, Service 이런 것들이 Component를 상속하여 만들어져있음
@Aspect
public class ValidationAdvice {
	
	//Before, After (public/private/protected 
	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// proceedingJoinPoint => apiController 함수의 모든 곳에 접근할 수 있는 변수
		// 함수보다 먼저 실행됨
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					
					throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
				}
			}
//			System.out.println(arg);// User의 toString에서 image빼기(무한참조때문에)
		}// 매개변수 뽑기
		
		// 다시 그 함수로 돌아가서 실행
		return proceedingJoinPoint.proceed();
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				if (bindingResult.hasErrors()) {
					Map<String,String> errorMap = new HashMap<>();
					
					for(FieldError error:bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
//						System.out.println("===========================");
//						System.out.println(error.getDefaultMessage());
//						System.out.println("===========================");
					}
					
					throw new CustomValidationException("유효성 검사 실패함", errorMap);
				}
			}
		}
		
		return proceedingJoinPoint.proceed();
	}
}
