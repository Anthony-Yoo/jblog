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
	
	public List<CategoryVo> selectCateId(String id) {
		System.out.println("AdminDao.selectCateId()");
		System.out.println(id);
		
		List<CategoryVo> cateList = session.selectList("Admin.selectId", id);
		System.out.println(cateList);
		
		return cateList;
	}
	
	public int insertCateVo(CategoryVo cateVo) {
		System.out.println("AdminDao.insertCateVo()");
		System.out.println(cateVo);
		return session.insert("Admin.insertcateVo", cateVo);
	}
	
	public CategoryVo selectCateNo(int cateNo) {
		System.out.println("AdminDao.selectCateNo()");
		
		return session.selectOne("Admin.selectCno", cateNo);
	}
	
	public int deleteCateVo(int cateNo) {
		System.out.println("AdminDao.deleteCateVo()");
		
		return session.delete("Admin.deleteCateVo",cateNo);
	}
	
	public int insertPostVo(PostVo postVo) {
		System.out.println("AdminDao.insertPostVo()");
		
		return session.insert("Admin.insertPostVo", postVo);		
	}
	
	public List<PostVo> selectPostList() {
		System.out.println("AdminDao.selectPostList()");
		
		return session.selectList("Admin.selectPostList");
	}
	
	public List<PostVo> selectListPost(int cateNo) {
		System.out.println("AdminDao.selectListPost()");		
		
		return session.selectList("Admin.selectListPost", cateNo);
	}
	
	public PostVo selectPost(int postNo) {
		System.out.println("AdminDao.selectPost()");
		
		return session.selectOne("Admin.selectPost", postNo);
	}
}
