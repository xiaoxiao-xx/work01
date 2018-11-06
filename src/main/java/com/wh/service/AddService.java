package com.wh.service;

import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;

import java.util.List;

public interface AddService {
	void addTravelUsers(TravelInfoT travelInfoT, List<TravelUserT> listTravelUserT, List<UserT> listUserT);
}
