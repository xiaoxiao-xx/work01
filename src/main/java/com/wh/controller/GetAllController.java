package com.wh.controller;


import com.wh.pojo.Business;
import com.wh.service.GetAllService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class GetAllController {
	@Resource
	private GetAllService getAllService;
	  @RequestMapping(value="/getAll", method = RequestMethod.GET)
	    @ResponseBody
	    public List<Business> getAll(){
	
		  List<Business> all = getAllService.selectAll();
	      return all;
	    }
}
