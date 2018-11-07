package com.wh.controller;

import com.wh.pojo.Information;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.service.UpdateService;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UpdateController {
	@Autowired
	private UpdateService updateService;
	
	@RequestMapping("update/travel")
	@ResponseBody
	public Result updateTravel(@RequestBody Information information){
		Result result = new Result();
		System.out.println(information);
		
		result.setStatus(0);
		result.setMessage("添加成功~");
		try {
			updateService.updateTravel(information);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(1);
			result.setMessage("添加失败~请稍后再试~");
			return result;
		}
		return result;
	}

	/**
	 *  编辑返回差旅信息
	 * @param request
	 * @return 结果集
	 * @throws Exception
	 */
	@RequestMapping("edit/back/travel")
	@ResponseBody
	public Result editBackTravel(HttpServletRequest request) throws Exception{

		Integer backNumb = Integer.parseInt(request.getParameter("backNumb"));
		String backTravelNum = request.getParameter("backTravelNum");
//		String backUserNum = request.getParameter("backUserNum");

		//添加的人员信息
		String[] backUserNum = request.getParameterValues("backName" );
		String[] backTime = request.getParameterValues("backTime" );
		String[] backTrasportation = request.getParameterValues("backTrasportation" );
		String[] backTrasportationPayMethod = request.getParameterValues("backTrasportation_payMethod" );
		String[] backTrasportationPayMoney = request.getParameterValues("backTrasportation_payMoney" );
		String[] backCostPayMethod = request.getParameterValues("backCost_payMethod" );
		String[] backCostPayMoney = request.getParameterValues("backCost_payMoney" );
		List<TravelUserT> travelUserList = new ArrayList<>();
		List<UserT> userList = new ArrayList<>();
		for(int i=0;i<backNumb;i++){
			TravelUserT travelUserT = new TravelUserT();
			travelUserT.setUserNum(backUserNum[i]);
			travelUserT.setGmtBack(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(backTime[i].replace('T', ' ')));
			travelUserT.setTrasportationBack(backTrasportation[i]);
			travelUserT.setBookingTypeBack(backTrasportationPayMethod[i]);
			travelUserT.setCostBack(new BigDecimal(backTrasportationPayMoney[i]));
			travelUserT.setStayBookingType(backCostPayMethod[i]);
			travelUserT.setCosStay(new BigDecimal(backCostPayMoney[i]));
			travelUserList.add(travelUserT);

			UserT userT = new UserT();
			userT.setUserNum(backUserNum[i]);
			userList.add(userT);
		}
//		String time = request.getParameter("time").replace('T', ' ');
//		String purpose = request.getParameter("purpose");

		Result result = new Result();
		updateService.editBackTravel(travelUserList,userList);
		result.setStatus(0);
		result.setMessage("编辑成功~");
		return result;
	}
	
	
}



