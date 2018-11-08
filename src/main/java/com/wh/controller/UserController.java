package com.wh.controller;

import com.wh.service.UserService;
import com.wh.vo.Page;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="/page/{currentPage}/{userKeyword}/{roleType}",method=RequestMethod.GET)
	@ResponseBody
	public Result findUserByPage(@PathVariable("currentPage") int currentPage,
								 @PathVariable("userKeyword") String userKeyword,
								 @PathVariable("roleType") String roleType) throws Exception{
		Result result=null;
		Page page=new Page();
		page.setCurrentPage(currentPage);
		page.setUserKeyword("undefined".equals(userKeyword) ? "%%" :"%"+userKeyword+"%");
		page.setRoleType(roleType);
		
		result=userService.findUsersByPage(page);
		
		return result;
	}
	
}
