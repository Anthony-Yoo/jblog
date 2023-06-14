package com.jblog.controller;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.AdminService;
import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.UserVo;
 

@Controller
public class AdminController {	
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id,Model model) {
		System.out.println("AdminController.adminBasic()");
		
		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-admin-basic";	
		
	}
	
	@RequestMapping(value="/restore",method = {RequestMethod.GET,RequestMethod.POST})
	public String restore(@RequestParam("file") MultipartFile file,@ModelAttribute BlogVo blogVo,HttpSession session) {
		System.out.println("AdminController.restore()");		
				
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		blogVo.setId(userVo.getId());
		System.out.println(blogVo);
		
		String urlId = blogVo.getId();
		adminService.restore(file, blogVo);		

		
//		model.addAttribute("saveName", saveName);
		
		return "redirect:/"+Normalizer.normalize(urlId,Normalizer.Form.NFD) +"/admin/basic";
	}
	/*
	@RequestMapping(value="/{id}/admin/writeForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String writeForm(@PathVariable("id") String id,Model model) {
		System.out.println("AdminController.writeForm()");
		
		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		CategoryVo cateVo = adminService.listFromId(id);
		
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-write";	
	}
	*/
	@RequestMapping("/{id}/admin/category")
	public String adminCategory(@PathVariable("id") String id,@ModelAttribute CategoryVo cateVo, Model model) {
		System.out.println("AdminController.adminCategory()");
		
		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);		
		
		List<CategoryVo> cateList = adminService.listFromId(id);
		System.out.println(cateList);
		
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("cateList", cateList);
		
		return "/blog/admin/blog-admin-cate";	
	}
	
	
	@RequestMapping("/category/insert")
	public String cateInsert(@PathVariable("id") String id,@ModelAttribute CategoryVo cateVo, Model model) {
		System.out.println("AdminController.cateInsert()");
		
		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		
		cateVo.setId(id); 
		adminService.addList(cateVo);
		System.out.println(cateVo);
		
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("cateVo", cateVo);
		
		return "/blog/admin/blog-admin-cate";	
	}
	
}
