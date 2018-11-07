package com.wh.service.impl;

import com.wh.dao.LoginMapper;
import com.wh.pojo.User;
import com.wh.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
	@Resource
	private LoginMapper loginMapper;
	
	@Override
	public Boolean login(User user) {
		Boolean flag = false;
		String userId = this.loginMapper.login(user);
		if(userId != null){
			flag = true;
		}
		return flag;
	}

}
