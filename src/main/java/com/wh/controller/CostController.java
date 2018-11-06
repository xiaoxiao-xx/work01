package com.wh.controller;

import com.wh.pojo.Information;
import com.wh.service.CostService;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CostController {
	@Autowired
	private CostService costService;
	
	
	@RequestMapping("/user/cost/{travelNum}")
	public Result cost(@PathVariable String travelNum){
		Result result = null;
		result = costService.findCost(travelNum);
		return result;
	}
	
	@RequestMapping("find/budget")
	@ResponseBody
	public Result findBudget(@RequestBody Information information){
		Result result= null;
		result = costService.findBudget(information);
		return result;
	}
	
	@RequestMapping("update/cost")
	@ResponseBody
	public Result updateCost(@RequestBody Information information){
		Result result= null;
		result = costService.updateCost(information);
		return result;
	}
}














