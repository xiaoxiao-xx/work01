package com.wh.dao;

import com.wh.pojo.Business;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface TravelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business business);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
    int  select(String filename);
    void delete(String filename);

    int countGetAll();

    List<Business> selectByPage(Map<String, Object> map);
}