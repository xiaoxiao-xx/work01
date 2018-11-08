package com.wh.service;

import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.vo.Page;
import com.wh.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TravelUserService {

	void addTravelUsers(TravelInfoT travelInfoT, List<TravelUserT> listTravelUserT, List<UserT> listUserT);

	void editBackTravel(List<TravelUserT> travelUserList, List<UserT> userList);

	Result findUsersByPage(Page page) throws Exception;

	String getBackedTravelUserInfo(HttpServletRequest request);
}

