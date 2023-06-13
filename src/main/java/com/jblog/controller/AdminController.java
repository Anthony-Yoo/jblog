package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;


@Controller
public class AdminController {	
	
	@Autowired
	private BlogService blogService;	
	
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id,Model model) {
		System.out.println("AdminController.adminBasic()");
		
		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-admin-basic";	
		
	}
	/*
	@RequestMapping(value="/upload",method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file,@ModelAttribute GalleryVo galleryVo,HttpSession session) {
		System.out.println("FileUpLoadController.upload()");	
		
		
		UserVo bulletinUser = (UserVo) session.getAttribute("successUser");
		galleryVo.setUser_no(bulletinUser.getNo());
		galleryVo.setUserName(bulletinUser.getName());		
		
		System.out.println(galleryVo);
		galleryServise.restore(file, galleryVo);
		
		
		
//		model.addAttribute("saveName", saveName);
		
		return "redirect:/gallery/list";
	}
	*/

}
