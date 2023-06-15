package com.jblog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.AdminService;
import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.JsonResult;
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
	public String restore(@RequestParam("file") MultipartFile file,@ModelAttribute BlogVo blogVo,HttpSession session) throws UnsupportedEncodingException {
		System.out.println("AdminController.restore()");		
				
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		blogVo.setId(userVo.getId());
		System.out.println(blogVo);
		
		String urlId = blogVo.getId();
		String encodeUID = encodedString(urlId);
		adminService.restore(file, blogVo);	
		
//		model.addAttribute("saveName", saveName);
		
		return "redirect:/"+encodeUID+"/admin/basic";
	}	
	
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
	
	@ResponseBody
	@RequestMapping("/category/insert")
	public JsonResult cateInsert(@ModelAttribute CategoryVo cateVo) {
		System.out.println("AdminController.cateInsert()");		
		
		CategoryVo resultCateVo = adminService.addList(cateVo);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(resultCateVo);
		System.out.println(jsonResult);
		
		return jsonResult;	
	}
	
	@ResponseBody
	@RequestMapping("/category/delete")
	public JsonResult cateDelete(@RequestParam("cateNo") int cateNo) {
		System.out.println("AdminController.cateDelete()");		
		
		int resultNo = adminService.deleteColumn(cateNo);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(resultNo);
		System.out.println(jsonResult);
		
		return jsonResult;	
	}
	
	public String encodedString(String name) throws UnsupportedEncodingException {
	    String encodedString = URLEncoder.encode(name, "UTF-8");

	    return encodedString;	
	}
	
	@RequestMapping(value="/{id}/admin/writeForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String writeForm(@PathVariable("id") String id,Model model) {
		System.out.println("AdminController.writeForm()");
		
		BlogVo blogVo = blogService.idcheck(id);
		System.out.println(blogVo);
		List<CategoryVo> cateList = adminService.listFromId(id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("cateList", cateList);
		
		return "/blog/admin/blog-admin-write";	
	}
	
	// @RequestMapping(value="/admin/write",)
	
}
