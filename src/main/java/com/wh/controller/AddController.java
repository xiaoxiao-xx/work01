package com.wh.controller;

import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.service.AddService;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddController {
	@Autowired
	private AddService addService;
	
	@RequestMapping(value="user/adds",method=RequestMethod.POST)
	@ResponseBody
	public Result addUser(HttpServletRequest request) throws Exception{
		//差旅信息表
		TravelInfoT travelInfoT = new TravelInfoT();
		Integer number = Integer.parseInt(request.getParameter("number"));
		String destination = request.getParameter("destination");
		String travel_number = request.getParameter("travel_number");
		String time = request.getParameter("time").replace('T', ' ');
//		String time = request.getParameter("time");
		String purpose = request.getParameter("purpose");
		travelInfoT.setTravelNum(travel_number);
		travelInfoT.setDestination(destination);
		travelInfoT.setCause(purpose);
		travelInfoT.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time));
		System.err.println(travelInfoT);
		
		//差旅人员表
		List<TravelUserT> listTravelUserT = new ArrayList<>();
		for(int i=1;i<=number;i++){
			
			TravelUserT travelUserT = new TravelUserT();
			travelUserT.setTravelNum(travel_number);
			travelUserT.setUserName(request.getParameter("name_"+i));
			travelUserT.setUserNum(request.getParameter("job_number_"+i));
			travelUserT.setGmtGo(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time));
			travelUserT.setTrasportationGo(request.getParameter("transportation_"+i));
			travelUserT.setCostGo(BigDecimal.valueOf(Double.parseDouble(request.getParameter("money_"+i))));
			travelUserT.setBookingTypeGo(request.getParameter("methods_"+i));
			listTravelUserT.add(travelUserT);
		}
		System.err.println(listTravelUserT);
		
		//员工表
		List<UserT> listUserT = new ArrayList<>();
		for(int i=1;i<=number;i++){
			UserT usert = new UserT();
			usert.setUserNum(request.getParameter("job_number_"+i));
			usert.setUserName(request.getParameter("name_"+i));
			usert.setState("1");
			listUserT.add(usert);
		}
		Result result = new Result();
		result.setStatus(0);
		result.setMessage("添加成功~");
		try {
			addService.addTravelUsers(travelInfoT,listTravelUserT,listUserT);
		} catch (Exception e) {
			result.setStatus(1);
			result.setMessage("添加失败~请稍后再试~");
			return result;
		}

		return result;
	}

}
