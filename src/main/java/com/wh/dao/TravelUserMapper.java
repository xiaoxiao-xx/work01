package com.wh.dao;

import com.wh.pojo.Information;
import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TravelUserMapper {

	void addTravelInfoT(TravelInfoT travelInfoT);
	int addTravelUsers(List<TravelUserT> list);
	String selectUser(UserT userT);
	void updateState(UserT userT);
	void addUser(UserT userT);

	void updateTravelUserT(Information information);
	void updateUserT(String userNum);
	void updateTravelUser(TravelUserT travelUserT);
	void updateUser(UserT userT);

	//分页(全部用户)
	int getCount(Page page);
	List<Information> getUsers(Page page);

	//分页(指定类型为出差中)
	int getCountByGo(Page page);
	List<Information> getUsersByGo(Page page);

	//分页(指定类型为已返回)
	int getCountByBack(Page page);
	List<Information> getUsersByBack(Page page);


	List<TravelUserT> getBackedTravelUserInfo(String travelNum);
}
