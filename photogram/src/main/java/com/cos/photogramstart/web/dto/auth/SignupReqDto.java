package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

//통신할 때 필요한 데이터를 담아두는 object

@Data
public class SignupReqDto {
	private String username;
	private String password;
	private String email;
	private String name;
}
