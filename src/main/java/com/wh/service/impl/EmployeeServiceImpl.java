package com.wh.service.impl;

import com.google.gson.Gson;
import com.wh.dao.EmployeeInfoTMapper;
import com.wh.pojo.vo.PageVO;
import com.wh.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author shawn
 * @create 2018-11-13 12:33
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Integer NUM_OF_ONE_PAGE = 10;
    private static final Gson GSON = new Gson();
    private static PageVO page = new PageVO();
    @Resource
    private EmployeeInfoTMapper eit;
    @Override
    public String getPage(HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
        //Map<String, String[]> map1 = request.getParameterMap();
        String keyword = request.getParameter("keyword");
        String personStatus = request.getParameter("person_status");
        String dep = request.getParameter("dep");
        String techDirec = request.getParameter("tech_direc");
        String techLev = request.getParameter("tech_lev");
        String rank = request.getParameter("rank");
        String empStatus = request.getParameter("emp_status");
        return null;
    }

    @Override
    public String listEmployeeInfo(HttpServletRequest request) {
        return null;
    }

    @Override
    public void exportInfo(HttpServletRequest request, HttpServletResponse response) {

    }
}
