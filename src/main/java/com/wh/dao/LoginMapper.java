package com.wh.dao;

import com.wh.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	String login(User user);
}
