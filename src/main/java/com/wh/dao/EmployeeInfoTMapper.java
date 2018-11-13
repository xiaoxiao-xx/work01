package com.wh.dao;

import com.wh.pojo.EmployeeInfoT;

public interface EmployeeInfoTMapper {
    int deleteByPrimaryKey(String employeeNo);

    int insert(EmployeeInfoT record);

    int insertSelective(EmployeeInfoT record);

    EmployeeInfoT selectByPrimaryKey(String employeeNo);

    int updateByPrimaryKeySelective(EmployeeInfoT record);

    int updateByPrimaryKey(EmployeeInfoT record);
}