package com.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jblog.dao.UserDao;
import com.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public int join(UserVo userVo) {
		System.out.println("UserService.join()");	
		
		return userDao.insertUser(userVo);
		
	}
	
	public UserVo idcheck(String id) {
		System.out.println("UserService.idcheck()");
		
		UserVo userVo = userDao.selectId(id);
		System.out.println(userVo);	
		
		return userVo;		
	}
	public UserVo login(UserVo userVo) {
		System.out.println("Service.login()");
					
		
		return userDao.selectUser(userVo);		
	}
	
	
}
