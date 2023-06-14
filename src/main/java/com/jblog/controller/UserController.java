package com.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jblog.service.BlogService;
import com.jblog.service.UserService;
import com.jblog.vo.UserVo;
import com.jblog.vo.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";		
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		
		userService.join(userVo);		
		blogService.create(userVo);
		
		return "user/joinSuccess";		
	}
	
	@ResponseBody
	@RequestMapping("/idcheck")
	public JsonResult idcheck(@RequestParam("id") String id) {
		System.out.println("UserController.idcheck()");
		System.out.println(id);
		
		UserVo userVo = userService.idcheck(id);
		
		boolean result;
		
		if(userVo == null) { //사용가능
			result = true;
		} else { 			//사용불가
			result = false;
		}	
		
		JsonResult jsonResult = new JsonResult();		
		jsonResult.success(result);		
		System.out.println(jsonResult);
		
		return jsonResult;
	}
	
	//로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo,HttpSession session) {
		System.out.println("UserController.login()");
		
		UserVo orgUser = userService.login(userVo);		
		System.out.println(orgUser);
		
		UserVo authUser = new UserVo();		
		  
		if (orgUser != null) {		
		System.out.println("로그인성공");
		
		authUser.setUserNo(orgUser.getUserNo());
		authUser.setUserName(orgUser.getUserName());
		authUser.setId(orgUser.getId());
		session.setAttribute("authUser", authUser);
		  
		return "main/index";
		
		} else { 
		System.out.println("로그인실패");
		
		return "redirect:/user/loginForm?result=fail";		
		}	
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		session.removeAttribute("autherUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
}
