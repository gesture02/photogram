package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional(readOnly = true) // 영속성 컨텍스트에서 변경감지를 해서 더티체킹, flush(readOnly true이면 안함)
	public Page<Image> imageStory(int principalId, Pageable pageable) {
		
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		return images;
	}
	
	@Transactional
	public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		// 통신, I/O가 일어날 때 -> 예외가 발생할 수 있음
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		//System.out.println(imageEntity); // image와 user 사이에 무한참조가 일어남
	}
}
