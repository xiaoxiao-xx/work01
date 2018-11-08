package com.wh.service;


import com.wh.vo.Page;
import com.wh.vo.Result;

public interface UserService {
	Result findUsersByPage(Page page) throws Exception;
}
