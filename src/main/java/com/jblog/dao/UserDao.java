package com.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession session;
	
	public int insertUser (UserVo userVo) {
		System.out.println("UserDao.insertUser()");		
		
		return session.insert("jblogUser.insert", userVo);
		
	}
	public UserVo selectId(String id) {
		System.out.println("UserDao.selectId()");
		
		return session.selectOne("jblogUser.selectId", id);		
	}	
}
