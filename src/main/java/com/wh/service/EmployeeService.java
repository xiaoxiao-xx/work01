package com.wh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shawn
 * @create 2018-11-13 12:32
 **/
public interface EmployeeService {
    String getPage(HttpServletRequest request);

    String listEmployeeInfo(HttpServletRequest request);

    void exportInfo(HttpServletRequest request, HttpServletResponse response);
}
