package com.wh.service;

import com.wh.pojo.Information;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;

import java.util.List;

public interface UpdateService {

	void updateTravel(Information information) throws Exception;

    void editBackTravel(List<TravelUserT> travelUserList, List<UserT> userList);
}
