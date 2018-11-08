package com.wh.service.impl;

import com.wh.dao.CostMapper;
import com.wh.pojo.Information;
import com.wh.service.CostService;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl implements CostService {

	@Autowired
	private CostMapper costMapper;

	@Override
	public Result findCost(String travelNum) {
		Result result = new Result();
		Information information= this.costMapper.findCost(travelNum);
		result.setStatus(0);
		result.setMessage("查询成功~");
		result.setData(information);
		return result;
	}

	@Override
	public Result findBudget(Information information) {
		Result result = new Result();
		Information cityCost = this.costMapper.findBudget(information);
		System.out.println("****"+cityCost);
		result.setData(cityCost);
		result.setStatus(0);
		result.setMessage("查询成功~");
		return result;
	}

	@Override
	public Result updateCost(Information information) {
		Result result = new Result();
		costMapper.updateCost(information);
		result.setStatus(0);
		result.setMessage("更新成功~");
		return result;
	}
	
	

}
