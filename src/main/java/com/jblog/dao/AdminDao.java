package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;

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
	
	public int insertcateVo(CategoryVo cateVo) {
		System.out.println("AdminDao.insertcateVo()");
		System.out.println(cateVo);
		return session.insert("Admin.insertcateVo", cateVo);
	}
	
	public CategoryVo selectCno(int cateNo) {
		System.out.println("AdminDao.selectCno()");
		
		return session.selectOne("Admin.selectCno", cateNo);
	}
	
	public int deleteVo(int cateNo) {
		System.out.println("AdminDao.deleteVo()");
		
		return session.delete("Admin.deleteVo",cateNo);
	}
	
	public int insertpostVo(PostVo postVo) {
		System.out.println("Admin.insertpostVo()");
		
		return session.insert("Admin.insertpostVo", postVo);		
	}
}
