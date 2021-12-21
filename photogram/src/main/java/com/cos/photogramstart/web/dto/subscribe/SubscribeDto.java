package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	private int id; // 구독취소 누를 사람 아이디
	private String username; // 누를 사람의 유저네임
	private String profileImageUrl; // 누를사람의 사진
	private Integer subscribeState;
	private Integer equalUserState;
}
