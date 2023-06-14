package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;

@Repository
public class AdminDao {
	
	@Autowired
	private SqlSession session;
	
	public int updateBlogImage(BlogVo blogVo) {
		System.out.println("AdminDao.insertBlogImage()");
		System.out.println(blogVo);
		
		return session.update("Admin.updateBlog", blogVo);
	}
	
	public List<CategoryVo> selectId(String id) {
		System.out.println("AdminDao.selectId()");
		System.out.println(id);
		
		List<CategoryVo> cateList = session.selectList("Admin.selectId", id);
		System.out.println(cateList);
		
		return cateList;
	}
	
	public int insertVo(CategoryVo cateVo) {
		System.out.println("AdminDao.insertVo()");
		System.out.println(cateVo);
		return session.insert("Admin.insertVo", cateVo);
	}
	
	public CategoryVo selectCno(int cateNo) {
		System.out.println("AdminDao.selectCno()");
		
		return session.selectOne("Admin.selectCno", cateNo);
	}
	
	
}
