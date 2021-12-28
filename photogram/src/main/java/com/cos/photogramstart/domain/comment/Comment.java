package com.cos.photogramstart.domain.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String content;
	
	// 댓글 하나 select : user, image 1개씩가져옴 (무리x)
	// 반면에 유저를 select : 유저의 모든 댓글, 모든 사진을 매번가져옴 -> 그래서 LAZY 사용
	// 한번 select 할 때 같이 오는게 여러개 -> LAZY, 하나 -> EAGER
	@JoinColumn(name="userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@JoinColumn(name="imageId")
	@ManyToOne
	private Image image;
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
