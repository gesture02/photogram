package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website;
	private String bio;
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	// 연관관계 주인이 아님 : 컬럼 만들지 말것
	// User를 SELECT할 때 해당 USERID로 등록된 image 다가져와
	// 단, LAZY -> USER를 SELECT할 때 해당 USERID로 등록된 IMAGE들을 가져오지 않음 : 나중에 getImages()함수의 image들이 호출될 때 가져옴
	// EAGER = USER를 SELECT할 때 해당 USERID로 등록된 IMAGE들을 전부 JOIN해서 가져옴
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Image> images; //양방향 매핑
	
	private LocalDateTime createDate;
	
	@PrePersist // 디비에 insert 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate=" + createDate + "]";
	}
	
	
}
