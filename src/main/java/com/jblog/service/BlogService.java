package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.BlogDao;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	public int create(String id) {
		System.out.println("BlogService.join()");
		
		return blogDao.insertId(id);
				
	}

}
