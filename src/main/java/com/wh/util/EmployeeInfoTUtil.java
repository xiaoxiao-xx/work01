package com.wh.util;

import com.wh.pojo.EmployeeInfoT;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeInfoTUtil {
    public EmployeeInfoT getEmployeeInfoT(HttpServletRequest request) throws Exception {
        EmployeeInfoT employeeInfoT = new EmployeeInfoT();
        employeeInfoT.setDepartment(Integer.parseInt(request.getParameter("department")));
        employeeInfoT.setEducation(request.getParameter("education"));
        employeeInfoT.setEmail(request.getParameter("email"));
        employeeInfoT.setEmployeeNo(request.getParameter("employeeNo"));
        employeeInfoT.setEmployeeRank(Integer.parseInt(request.getParameter("employeeRank")));
        employeeInfoT.setEmploymentStatus(Integer.parseInt(request.getParameter("employmentStatus")));
        employeeInfoT.setGmtBitrh(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("gmtBitrh")));
        employeeInfoT.setGmtEntry(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("entrytime")));
        employeeInfoT.setHobby(request.getParameter("hoby"));
        employeeInfoT.setIdCard(request.getParameter("idCard"));
        employeeInfoT.setName(request.getParameter("name"));
        employeeInfoT.setPersonStatus(0);
        employeeInfoT.setPosition(request.getParameter("position"));
        employeeInfoT.setSex(request.getParameter("sex"));
        employeeInfoT.setSuperName(request.getParameter("superName"));
        employeeInfoT.setSuperNo(request.getParameter("superNo"));
        employeeInfoT.setTechDirec(request.getParameter("techDirec"));
        employeeInfoT.setTechLev(Integer.parseInt(request.getParameter("techLev")));
        employeeInfoT.setTel(request.getParameter("tel"));
        employeeInfoT.setTravelStatus(0);
        return employeeInfoT;
    }
}
