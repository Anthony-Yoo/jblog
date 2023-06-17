package com.jblog.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.dao.AdminDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	
	String saveDir = "C:\\javaStudy\\upload";
	
	public int restore(MultipartFile file,BlogVo blogVo) {
		System.out.println("AdminService.restore()");
		System.out.println(blogVo);
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
			blogVo.setLogoFile(saveName);		
			
		return adminDao.updateBlogImage(blogVo);			
	}

	public List<CategoryVo> listCateFromId(String id) {
		System.out.println("AdminService.listCateFromId()");
						
		return adminDao.selectCateId(id);
	}
	
	public CategoryVo addList(CategoryVo cateVo) {
		System.out.println("AdminService.addList()");
		
		adminDao.insertCateVo(cateVo);
		int cateNo = cateVo.getCateNo();
		System.out.println(cateNo);
		CategoryVo resultCateVo = adminDao.selectCateNo(cateNo);
		System.out.println(resultCateVo);
		
		return resultCateVo;
	}
	
	public int deleteColumn(int cateNo) {
		System.out.println("AdminService.deleteColumn()");
				
		return adminDao.deleteCateVo(cateNo);
	}
	
	public int write(PostVo postVo) {
		System.out.println("AdminService.write()");		
		
		return adminDao.insertPostVo(postVo);
	}
	
	public List<PostVo> listPost() {
		System.out.println("AdminService.listPost()");
		
		return adminDao.selectPostList();
	}
	
	public List<PostVo> selectListPost(int cateNo) {
		System.out.println("AdminService.selectListPost()");
		
		return adminDao.selectListPost(cateNo);
	}
	
	public PostVo selectPost(int postNo) {
		System.out.println("AdminService.selectPost()");
		
		return adminDao.selectPost(postNo);
	}
}
