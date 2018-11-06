package com.wh.dao;

import com.wh.pojo.Information;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CostMapper {

	Information findCost(String travelNum);

	Information findBudget(Information information);

	void updateCost(Information information);

}
