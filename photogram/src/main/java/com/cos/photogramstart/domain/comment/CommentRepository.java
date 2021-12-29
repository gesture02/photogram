package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
//	//리턴 타입이 Integer이기 때문에 해당 함수는 오류가 난다. 따라서 nativeQuery는 사용 불가능
//	@Modifying
//	@Query(value="INSERT INTO comment(content, imageId, userId, createDate) VALUES(:content, :imageId, :userId, now())", nativeQuery = true)
//	Comment mSave(String content, int imageId, int userId);
}
