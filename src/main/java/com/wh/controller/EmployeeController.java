package com.wh.controller;

import com.wh.pojo.EmployeeInfoT;
import com.wh.service.EmployeeService;
import com.wh.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shawn
 * @create 2018-11-13 12:31
 **/
@Controller
@RequestMapping("/employeeController")
public class EmployeeController {
    @Resource
    private EmployeeService emps;

    /**
     * 根据条件查询需要显示的雇员信息的分页数据
     * 1.keyword 关键字
     * 2.person_status 在职或离职
     * 3.dep 部门
     * 4.tech_direc 技术方向
     * 5.tech_lev 技术等级 初 中 高
     * 6.rank 职级 执行级 关联级 部门级 经营级
     * 7.emp_status 雇佣状态 正编 外协 实习
     * @param request
     * @return
     */
    @RequestMapping("/getPage.ajax")
    @ResponseBody
    public String getPage(HttpServletRequest request){
        return emps.getPage(request);
    }


    /**
     * 分页查询雇员信息
     * @param request
     * @return 雇员信息封装返回到前端
     */
    @RequestMapping(value = "/listEmployeeInfo.ajax",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listEmployeeInfo(HttpServletRequest request){
        return emps.listEmployeeInfo(request);
    }

    @RequestMapping("/exportEmployeeInfo.do")
    public void exportEmployeeInfo(HttpServletRequest request, HttpServletResponse response){
        emps.exportInfo(request,response);
    }

    /**
     *  新增员工
     * @return 新增的反馈信息
     */
    @RequestMapping("/add/exportEmployeeInfo.ajax")
    @ResponseBody
    public Result addExportEmployeeInfo(HttpServletRequest request,EmployeeInfoT employeeInfoT) throws Exception{
        Result result = null;
        result = emps.addExportEmployeeInfo(employeeInfoT);
        return result;
    }

}
