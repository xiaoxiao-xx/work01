package com.wh.service.impl;

import com.wh.dao.UpdateMapper;
import com.wh.pojo.Information;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.service.UpdateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {
	@Resource
	private UpdateMapper updateMapper;
	
	@Override
	@Transactional
	public void updateTravel(Information information) throws Exception {
		if(information.getGmtBack()!=null){
			Double allTime = 0.0;
			SimpleDateFormat formatday = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formattime = new SimpleDateFormat("HH");
			Date go = information.getGmtGo();
			Date back = information.getGmtBack();
			Long day = (formatday.parse(formatday.format(back)).getTime()-formatday.parse(formatday.format(go)).getTime())/(1000*60*60*24)-1;
			if((Integer.parseInt(formattime.format(go))-12)>0){
				allTime+=0.5;
			}else{
				allTime+=1;
			}
			if((Integer.parseInt(formattime.format(back))-12)>0){
				allTime+=1;
			}else{
				allTime+=0.5;
			}
			allTime+=day;
			information.setStayDays(allTime.toString());
		}
		updateMapper.updateTravelUserT(information);
		
		String userNums = information.getUserNum();
		String[] userNum = userNums.split(",");
		for(int i=0;i<userNum.length;i++){
			updateMapper.updateUserT(userNum[i]);
		}
		
		
	}

	@Override
	public void editBackTravel(List<TravelUserT> travelUserList, List<UserT> userList) {
//		for(int i=0;i<travelUserList.size();i++){
//			updateMapper.updateTravelUser(travelUserList.get(i));
//		}
//		for(int i=0;i<userList.size();i++){
//			updateMapper.updateUser(userList.get(i));
//		}
	}

}
