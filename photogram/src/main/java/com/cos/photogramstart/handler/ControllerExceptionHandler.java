package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		//1. 클라이언트에게 응답 : Script가 좋음
		//2. Ajax통신(개발자가 응답받음) - CMRespDto
		//3. Android통신(개발자가 응답받음) - CMRespDto
		return Script.back(e.getErrorMap().toString());
	}
}
