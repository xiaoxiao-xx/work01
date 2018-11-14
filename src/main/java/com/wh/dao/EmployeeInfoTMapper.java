package com.wh.dao;

import com.wh.pojo.EmployeeInfoT;
import com.wh.pojo.vo.EmployeeInfoVO;

import java.util.HashMap;
import java.util.List;

public interface EmployeeInfoTMapper {
    int deleteByPrimaryKey(String employeeNo);

    int insert(EmployeeInfoT record);

    int insertSelective(EmployeeInfoT record);

    EmployeeInfoT selectByPrimaryKey(String employeeNo);

    int updateByPrimaryKeySelective(EmployeeInfoT record);

    int updateByPrimaryKey(EmployeeInfoT record);

    int countEmployeeInfo(HashMap<String, Object> map);

    List<EmployeeInfoT> listEmployeeInfo(HashMap<String, Object> map);

    List<EmployeeInfoVO> selectAllEmployee();
}
