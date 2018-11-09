package com.wh.controller;

import com.wh.service.SelectNumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SelectNumController {
    @Resource
    private SelectNumService selectNumService;
    @RequestMapping(value="/selectNum.ajax", method = RequestMethod.POST)
    @ResponseBody
    public List<String>  selectNum(HttpServletRequest request){

        List<String> namelist=selectNumService.selectNum(request);
        return namelist;

    }
}
