package com.wh.controller;

import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.service.TravelUserService;
import com.wh.vo.Page;
import com.wh.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TravelUserController {
	private static final String ORIGIN = "成都";
	@Resource
	private TravelUserService travelUserService;

	/**
	 * 添加出差人员
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="user/adds",method=RequestMethod.POST)
	@ResponseBody
	public Result addUser(HttpServletRequest request) throws Exception{
		Result result = new Result();
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
		travelInfoT.setOrigin(ORIGIN);
		System.out.println(travelInfoT);
		System.out.println(number);
		String[] addNames = request.getParameterValues("addName");
		String[] addJobNumbers = request.getParameterValues("addJobNumber");
		String[] addTransportations = request.getParameterValues("addTransportation");
		String[] addPayMethods = request.getParameterValues("addPayMethod");
		String[] addMonies = request.getParameterValues("addMoney");
		//差旅人员表

		if(addNames==null){
			result.setStatus(1);
			result.setMessage("出差人员为空，请添加");
			return result;
		}
		List<TravelUserT> listTravelUserT = new ArrayList<>();
		for (int i=0;i<addJobNumbers.length-1;i++){
			for (int j=i+1;j<addJobNumbers.length;j++){
				if(addJobNumbers[i].equals(addJobNumbers[j])){
					result.setStatus(1);
					result.setMessage("新增人员重复，请修改后提交");
					return result;
				}
			}
		}
		for(int i=0;i<number;i++){
			if(addJobNumbers[i].equals("请选择工号")){
				result.setStatus(1);
				result.setMessage("请选择工号");
				return result;
			}
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

		result.setStatus(0);
		result.setMessage("添加成功~");
		try {
			travelUserService.addTravelUsers(travelInfoT,listTravelUserT,listUserT);
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
		Result result = new Result();
		Integer backNumb = Integer.parseInt(request.getParameter("backNumb"));
		String backTravelNum = request.getParameter("backTravelNum");

		//添加的人员信息
		String[] backUserNum = request.getParameterValues("backName" );
		String[] backTime = request.getParameterValues("backTime" );
		String[] backTrasportation = request.getParameterValues("backTrasportation" );
		String[] backTrasportationPayMethod = request.getParameterValues("backTrasportation_payMethod" );
		String[] backTrasportationPayMoney = request.getParameterValues("backTrasportation_payMoney" );
		String[] backCostPayMethod = request.getParameterValues("backCost_payMethod" );
		String[] backCostPayMoney = request.getParameterValues("backCost_payMoney" );
		String[] stayDays = request.getParameterValues("stay_days" );
		List<TravelUserT> travelUserList = new ArrayList<>();
		List<UserT> userList = new ArrayList<>();
		if(backTime==null){
			result.setStatus(1);
			result.setMessage("返回人员为空，请添加");
			return result;
		}
		for (int i=0;i<backUserNum.length-1;i++){
			for (int j=i+1;j<backUserNum.length;j++){
				if(backUserNum[i].equals(backUserNum[j])){
					result.setStatus(1);
					result.setMessage("返回人员重复，请修改后提交");
					return result;
				}
			}
		}

		for(int i=0;i<backNumb;i++){
			TravelUserT travelUserT = new TravelUserT();
			travelUserT.setTravelNum(backTravelNum);
			travelUserT.setUserNum(backUserNum[i]);
			travelUserT.setGmtBack(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(backTime[i].replace('T', ' ')));
			travelUserT.setTrasportationBack(backTrasportation[i]);
			travelUserT.setBookingTypeBack(backTrasportationPayMethod[i]);
			travelUserT.setCostBack(new BigDecimal(backTrasportationPayMoney[i]));
			travelUserT.setStayBookingType(backCostPayMethod[i]);
			travelUserT.setCosStay(new BigDecimal(backCostPayMoney[i]));
			travelUserT.setStayDays(stayDays[i]);
			travelUserList.add(travelUserT);

			UserT userT = new UserT();
			userT.setUserNum(backUserNum[i]);
			userList.add(userT);
		}

		result.setStatus(0);
		result.setMessage("编辑成功~");
		try{
			travelUserService.editBackTravel(travelUserList,userList);
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(1);
			result.setMessage("添加失败~请稍后再试~");
			return result;
		}
		return result;
	}

	/**
	 * 查询员工差旅信息
	 * @param currentPage
	 * @param userKeyword
	 * @param roleType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/page/{currentPage}/{userKeyword}/{roleType}/{selectTime}",method=RequestMethod.GET)
	@ResponseBody
	public Result findUserByPage(@PathVariable("currentPage") int currentPage,
								 @PathVariable("userKeyword") String userKeyword,
								 @PathVariable("roleType") String roleType,
								 @PathVariable("selectTime") String selectTime) throws Exception{
		Result result=null;
		Page page=new Page();
		page.setCurrentPage(currentPage);
		page.setUserKeyword("undefined".equals(userKeyword) ? "%%" :"%"+userKeyword+"%");
		String selectTime2 = selectTime.replaceAll("-","");
		page.setSelectTime("undefined".equals(selectTime) ? "none" : selectTime2);
		page.setRoleType(roleType);

		result=travelUserService.findUsersByPage(page);

		return result;
	}

	/**
	 * 根据差旅编号获取已返回的差旅人员信息，
	 * 返回
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/back/userInfo.ajax",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String userInfo(HttpServletRequest request){
		return travelUserService.getBackedTravelUserInfo(request);
	}

	/**
	 * 获取城市信息
	 * @return
	 */
	@RequestMapping(value = "get/city/cityCostStandard_t.ajax",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getCity(){
		String str = null;
		try{
			str = travelUserService.getCity();
		}catch (Exception e){
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 获取已出差人员
	 * @return
	 */
	@RequestMapping(value = "get/state/user_t.ajax",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getState(HttpServletRequest request){
		String getNum = request.getParameter("getNum");
		String str = null;
		try{
			str = travelUserService.getState(getNum);
		}catch (Exception e){
			e.printStackTrace();
		}
		return str;
	}
}
