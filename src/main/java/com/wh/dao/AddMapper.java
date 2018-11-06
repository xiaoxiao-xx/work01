package com.wh.dao;

import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AddMapper {
	void addTravelInfoT(TravelInfoT travelInfoT);
	int addTravelUsers(List<TravelUserT> list);
	String selectUser(UserT userT);
	void updateState(UserT userT);
	void addUser(UserT userT);
}
