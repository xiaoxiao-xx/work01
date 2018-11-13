package com.wh.service.impl;

import com.google.gson.Gson;
import com.wh.dao.EmployeeInfoTMapper;
import com.wh.pojo.EmployeeInfoT;
import com.wh.pojo.vo.PageVO;
import com.wh.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

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
        HashMap<String,Object> map = queryMap(request);
        int totalRecords = eit.countEmployeeInfo(map);
        int total = totalRecords / NUM_OF_ONE_PAGE;
        total = totalRecords % NUM_OF_ONE_PAGE == 0 ? total : total + 1;
        page = new PageVO(total + "", totalRecords + "", NUM_OF_ONE_PAGE + "");
        return GSON.toJson(page);
    }


    @Override
    public String listEmployeeInfo(HttpServletRequest request) {
        HashMap<String,Object> map = queryMap(request);
        String pn = request.getParameter("pn");
        int offset = (Integer.parseInt(pn) - 1) * NUM_OF_ONE_PAGE;
        int size = NUM_OF_ONE_PAGE;
        map.put("offset", offset);
        map.put("size", size);
        List<EmployeeInfoT> listEmployeeInfo = eit.listEmployeeInfo(map);
        return GSON.toJson(listEmployeeInfo);
    }

    @Override
    public void exportInfo(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 查询条件map
     * @param request
     * @return
     */
    private HashMap<String, Object> queryMap(HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
        String keyword = request.getParameter("keyword");
        keyword = "%" + keyword + "%";
        String personStatus = request.getParameter("person_status");
        String dep = request.getParameter("dep");
        String techDirec = request.getParameter("tech_direc");
        String techLev = request.getParameter("tech_lev");
        String rank = request.getParameter("rank");
        String empStatus = request.getParameter("emp_status");
        map.put("keyword",keyword);
        map.put("personStatus",personStatus);
        map.put("dep",dep);
        map.put("techDirec",techDirec);
        map.put("techLev",techLev);
        map.put("rank",rank);
        map.put("empStatus",empStatus);
        return map;
    }
}
