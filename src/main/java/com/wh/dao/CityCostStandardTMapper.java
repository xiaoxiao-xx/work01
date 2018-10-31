package com.wh.dao;

import com.wh.pojo.CityCostStandardT;

public interface CityCostStandardTMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CityCostStandardT record);

    int insertSelective(CityCostStandardT record);

    CityCostStandardT selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CityCostStandardT record);

    int updateByPrimaryKey(CityCostStandardT record);
}