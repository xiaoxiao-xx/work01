package com.wh.dao;

import com.wh.pojo.TravelUserT;
import com.wh.pojo.vo.TravelCostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TravelUserTMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TravelUserT record);

    int insertSelective(TravelUserT record);

    TravelUserT selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TravelUserT record);

    int updateByPrimaryKey(TravelUserT record);

    int countTravelInfo(String time);

    List<TravelCostVO> listTravelCost(Map<String, Object> map);

    List<TravelCostVO> listTravelInfoOneMonth(String time);
}