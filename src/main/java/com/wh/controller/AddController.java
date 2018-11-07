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
		String purpose = request.getParameter("purpose");
		travelInfoT.setTravelNum(travel_number);
		travelInfoT.setDestination(destination);
		travelInfoT.setCause(purpose);
		travelInfoT.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time));
		System.out.println(travelInfoT);
		System.out.println(number);
		String[] addNames = request.getParameterValues("addName");
		String[] addJobNumbers = request.getParameterValues("addJobNumber");
		String[] addTransportations = request.getParameterValues("addTransportation");
		String[] addPayMethods = request.getParameterValues("addPayMethod");
		String[] addMonies = request.getParameterValues("addMoney");
		//差旅人员表

		List<TravelUserT> listTravelUserT = new ArrayList<>();
		for(int i=0;i<number;i++){
			TravelUserT travelUserT = new TravelUserT();
			travelUserT.setTravelNum(travel_number);
			travelUserT.setUserName(addNames[i]);
			travelUserT.setUserNum(addJobNumbers[i]);
			travelUserT.setGmtGo(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time));
			travelUserT.setTrasportationGo(addTransportations[i]);
			travelUserT.setCostGo(new BigDecimal(addMonies[i]));
			travelUserT.setBookingTypeGo(addPayMethods[i]);
			listTravelUserT.add(travelUserT);
		}
		System.out.println(listTravelUserT);
		//员工表
		List<UserT> listUserT = new ArrayList<>();
		for(int i=0;i<number;i++){
			UserT usert = new UserT();
			usert.setUserNum(addJobNumbers[i]);
			usert.setUserName(addNames[i]);
			usert.setState("1");
			listUserT.add(usert);
		}
		System.out.println(listUserT);
		Result result = new Result();
		result.setStatus(0);
		result.setMessage("添加成功~");
		try {
			addService.addTravelUsers(travelInfoT,listTravelUserT,listUserT);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(1);
			result.setMessage("添加失败~请稍后再试~");
			return result;
		}
		return result;
	}

}
