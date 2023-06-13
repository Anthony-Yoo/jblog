package com.jblog.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.dao.BlogDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;	
	
	public int create(UserVo userVo) {
		System.out.println("BlogService.join()");
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName()+"님의 블로그입니다.");
		blogVo.setLogoFile("defaultImage setting");
		
		return blogDao.insertId(blogVo);				
	}
	
	public BlogVo idcheck(String id) {
		System.out.println("BlogService.idcheck()");		
		
		return blogDao.selectId(id);	
	}
	/*
	public int restore(MultipartFile file,GalleryVo galleryVo) {
		System.out.println("FileUpLoadService.restore()");
		System.out.println(file.getOriginalFilename());
		
			//original 파일이름
			String orgName = file.getOriginalFilename();
			System.out.println("orgName : "+orgName);
			//확장자
			String exName =  orgName.substring(orgName.lastIndexOf('.'));
			System.out.println("exName : "+exName);
			//저장파일 이름
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
			System.out.println("saveName : "+saveName);						
			//파일패스
			String filePath = saveDir + "\\" + saveName;
			System.out.println("filePath : " + filePath);			
			//파일사이즈
			long fileSize = file.getSize();
			System.out.println(fileSize);
		
		//file upload (하드디스트저장)
		
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout =  new BufferedOutputStream(out);
				bout.write(fileData);				
				bout.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//db에 저장		
			galleryVo.setFilePath(filePath);
			galleryVo.setOrgName(orgName);
			galleryVo.setSaveName(saveName);
			galleryVo.setFileSize(fileSize);	
			
			
		return galleryDao.insertGallery(galleryVo);
			
	}
	*/

}
