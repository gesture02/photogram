package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

// NotNull = null값 체크
// NotEmpty = 빈 값이거나 null 체크
// NotBlank = 빈 값이거나 null 체크, 빈 공백(" ")

@Data
public class CommentDto {
	
	@NotBlank // 빈 값(""), null, space(" ")
	private String content;
	@NotNull //빈 값이거나 null
	private Integer imageId;
	
	//toEntity가 필요없음
}
