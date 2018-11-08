package com.wh.service;

import com.wh.pojo.Information;
import com.wh.vo.Result;

public interface CostService {

	Result findCost(String travelNum);

	Result findBudget(Information information);

	Result updateCost(Information information);

}
