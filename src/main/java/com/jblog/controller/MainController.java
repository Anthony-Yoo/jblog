package com.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jblog.service.BlogService;
import com.jblog.service.UserService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	
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
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
}
