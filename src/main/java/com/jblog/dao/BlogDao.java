package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession session;
	
	public int insertId(BlogVo blogVo) {
		System.out.println("BlogDao.insertId()");
		
		return session.insert("Blog.insertVo", blogVo);		
	}
	
	public BlogVo selectId(String id) {
		System.out.println("BlogDao.selectId()");
		
		return session.selectOne("Blog.selectId", id);	
	}

}
