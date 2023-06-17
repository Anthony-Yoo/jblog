package com.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jblog.service.AdminService;
import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;

@Controller
public class MainController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "",method = {RequestMethod.GET,RequestMethod.POST})	
	public String index() {
		System.out.println("MainController.index()");
		
		return "main/index";
	}
	
	@RequestMapping("/{id}")
	public String blogMain(@PathVariable("id") String id,
						   @RequestParam(value="postNo", required = false, defaultValue = "-1") int postNo,
						   @RequestParam(value="cateNo", required = false, defaultValue = "-1") int cateNo, Model model) {
		System.out.println("MainController.blogMain()");		

		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		
		List<CategoryVo> cateList = adminService.listCateFromId(id);
		int lastCateNo = cateList.get(0).getCateNo();
		System.out.println("마지막 카테고리 넘버는" + lastCateNo);	
		
		List<PostVo> selectPostList = null;
		PostVo selectPost = null;
		////////////////////cateNo디폴트여부//////////////////////////////
		if( cateNo != -1 ) {
			
		selectPostList =adminService.selectListPost(cateNo);
		System.out.println("selectPostList : " + cateNo);		
		
		}else {		
		
		selectPostList =adminService.selectListPost(lastCateNo);
		System.out.println("selectPostList : " + selectPostList);
		}
		
		/////////////////////////postNo디포트여부//////////////////////////////////
		if( postNo != -1 ) {
		selectPost = adminService.selectPost(postNo);
		System.out.println("선택 포스트는 " + selectPost);		
		}else {	
			
			if (selectPostList.size() == 0 ) {
				
				System.out.println("포스트글이없어용~");
				selectPost = null;
				
			}else {
				int lastPostNo = selectPostList.get(0).getPostNo();
				System.out.println("최근 포스트 넘버는" + lastPostNo);	
				
				selectPost = adminService.selectPost(lastPostNo);
				System.out.println("선택 포스트는 " + selectPost);			
			}
		}		
		
		model.addAttribute("cateList", cateList);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("selectPostList", selectPostList);
		model.addAttribute("selectPost", selectPost);
		
		return "blog/blog-main";
	}	
	
}
