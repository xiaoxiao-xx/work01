package com.wh.dao;

import com.wh.pojo.Information;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UpdateMapper {

	void updateTravelUserT(Information information);

	void updateUserT(String userNum);

//	void updateTravelUser(TravelUserT travelUserT);
//
//	void updateUser(UserT userT);
}
