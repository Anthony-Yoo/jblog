package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		blogVo.setLogoFile("spring-logo.jpg");
		
		return blogDao.insertId(blogVo);				
	}
	
	public BlogVo idcheck(String id) {
		System.out.println("BlogService.idcheck()");		
		
		return blogDao.selectId(id);	
	}
	/*
	
	*/

}
