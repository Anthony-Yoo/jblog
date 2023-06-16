package com.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jblog.service.AdminService;
import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;

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
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("MainController.blogMain()");		

		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		
		List<CategoryVo> cateList = adminService.listFromId(id);
		
		model.addAttribute("cateList", cateList);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
}
