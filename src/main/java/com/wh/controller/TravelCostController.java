package com.wh.controller;

import com.wh.service.TravelCostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/travelCostController")
public class TravelCostController {
    @Resource
    private TravelCostService tcs;

    /**
     * 获取分页相关数据，页数，总条数，每页条数
     * @param request
     * @return
     */
    @RequestMapping("/getPage.ajax")
    @ResponseBody
    public String getPage(HttpServletRequest request){
        return tcs.getPage(request);
    }

    /**
     * 查询差旅记录（根据年月查）
     * @param request
     * @return 差旅明细记录的josn数据
     */
    @RequestMapping(value = "/listTravel_cost.ajax",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listTravel_cost(HttpServletRequest request){
        return tcs.listTravel_cost(request);
    }

    @RequestMapping("/exportInfo.do")
    public void exportInfo(HttpServletRequest request, HttpServletResponse response){
        tcs.exportInfo(request,response);
    }

}
