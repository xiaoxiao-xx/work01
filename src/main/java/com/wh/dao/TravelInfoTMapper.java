package com.wh.dao;

import com.wh.pojo.TravelInfoT;

public interface TravelInfoTMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TravelInfoT record);

    int insertSelective(TravelInfoT record);

    TravelInfoT selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TravelInfoT record);

    int updateByPrimaryKey(TravelInfoT record);
}