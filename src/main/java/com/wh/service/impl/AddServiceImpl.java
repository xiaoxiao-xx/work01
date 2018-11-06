package com.wh.service.impl;

import com.wh.dao.AddMapper;
import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddServiceImpl implements AddService {
	@Autowired
	private AddMapper addMapper;
	@Override
	@Transactional
	public void addTravelUsers(TravelInfoT travelInfoT, List<TravelUserT> listTravelUserT, List<UserT> listUserT) {
		
		addMapper.addTravelInfoT(travelInfoT);
		addMapper.addTravelUsers(listTravelUserT);
		for(int i=0;i<listUserT.size();i++){
			if(addMapper.selectUser(listUserT.get(i))!=null){
				addMapper.updateState(listUserT.get(i));
			}else{
				addMapper.addUser(listUserT.get(i));
			}
		}
		
	}

}
