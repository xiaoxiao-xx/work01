package com.wh.controller;

import com.wh.pojo.User;
import com.wh.service.LoginService;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;



@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("user/login/{name}/{pwd}")
	@ResponseBody
	public Result login(@PathVariable("name") String loginName,@PathVariable("pwd")String password,HttpSession session){
		System.err.println("name= "+loginName+", "+"password= "+password);
		Result result=new Result();
		User user=new User();
		user.setUserName(loginName);
		user.setPassword(password);
		
		if(this.loginService.login(user)){
			result.setStatus(0);
			session.setAttribute("loginName", loginName);
		}else{
			result.setStatus(1);
			result.setMessage("用户名和密码错误!");
		}
		return result;
	}
}
