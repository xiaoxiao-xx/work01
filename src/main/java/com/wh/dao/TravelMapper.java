package com.wh.dao;

import com.wh.pojo.Business;

import java.util.List;

public interface TravelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business business);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
    int  select(String filename);
    void delete(String filename);
    List<Business> selectAll();
}