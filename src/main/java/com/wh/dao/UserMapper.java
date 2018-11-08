package com.wh.dao;

import com.wh.pojo.Information;
import com.wh.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
	//	public String login(User user);
	//分页(全部用户)
	public int getCount(Page page);
	public List<Information> getUsers(Page page);

	//分页(指定类型为出差中)
	public int getCountByGo(Page page);
	public List<Information> getUsersByGo(Page page);

	//分页(指定类型为已返回)
	public int getCountByBack(Page page);
	public List<Information> getUsersByBack(Page page);
	//删除
	//	public int deleteUser(String userId);
	//	public int deleteUserRole(String userId);
	//删除指定用户id的视频和缓存
	//	public int deleteHistoryCacheByUserId(String userId);

	//根据用户id查询用户信息
	//	public Information findUserById(String userId);
	//添加用户
	//	public int addUser(Information user);
	//添加用户和角色中间表
	//	public int addUserRole(UserRole ur);
	//添加用户信息
	//	public int updateUser(Information user);

	//查询所有数据
	//	public List<Information> findAllUsers();

}
