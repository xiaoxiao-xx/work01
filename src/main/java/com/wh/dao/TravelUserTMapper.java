package com.wh.dao;

import com.wh.pojo.TravelUserT;

public interface TravelUserTMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TravelUserT record);

    int insertSelective(TravelUserT record);

    TravelUserT selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TravelUserT record);

    int updateByPrimaryKey(TravelUserT record);
}