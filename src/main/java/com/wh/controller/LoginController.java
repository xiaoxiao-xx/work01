package com.wh.controller;

import com.wh.pojo.User;
import com.wh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login.ajax")
	@ResponseBody
	public String  login(HttpServletRequest request){
		String loginName = request.getParameter("lName");
		String password = request.getParameter("lPwd");
		System.err.println("name= "+loginName+", "+"password= "+password);
		User user=new User();
		user.setUserName(loginName);
		user.setPassword(password);
		if(this.loginService.login(user)){
			request.getSession().setAttribute("loginName", loginName);
			return "yes";
		}
		return "no";
	}
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request){
	    request.getSession().invalidate();
	    return "redirect:/login.html";
    }
}
