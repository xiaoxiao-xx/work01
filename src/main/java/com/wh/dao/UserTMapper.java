package com.wh.dao;

import com.wh.pojo.UserT;

public interface UserTMapper {
    int deleteByPrimaryKey(String userNum);

    int insert(UserT record);

    int insertSelective(UserT record);

    UserT selectByPrimaryKey(String userNum);

    int updateByPrimaryKeySelective(UserT record);

    int updateByPrimaryKey(UserT record);
}