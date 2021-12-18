package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User userProfile(int userId) {
		//SELECT * FROM image WHERE userid = :userid;
		
		User userEntity = userRepository.findById(userId)
				.orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지 입니다");
		});
		
		userEntity.getImages();
		
		return userEntity;
	}
	
	@Transactional
	public User update(int id, User user) {
		//1. 영속화
		User userEntity = userRepository.findById(id)
				.orElseThrow(()->{// new supplier<IllegalArgumentException> - public IllegalArgumentException get()
					return new CustomValidationApiException("찾을 수 없는 id입니다.");
				});
		//2. 영속화된 오브젝트 수정
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		//3. 더티체킹(업데이트완료)
		return userEntity;
	}
}
