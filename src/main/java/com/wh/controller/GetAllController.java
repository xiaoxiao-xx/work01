package com.wh.controller;

import com.wh.service.GetAllService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/getALL")
public class GetAllController {
	@Resource
	private GetAllService getAllService;

	/**
	 * 获取分页相关数据，页数，总条数，每页条数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage.ajax")
	@ResponseBody
	public String getPage(HttpServletRequest request){


		return getAllService.getPage(request);
	}

	/**
	 *
	 */
	@RequestMapping(value = "/listTravel.ajax",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String listTravel_cost(HttpServletRequest request){

		return getAllService.listTravel(request);
	}

/**
 *
 */
@RequestMapping("/selectExcel.ajax")
@ResponseBody
public String selectExcel(HttpServletRequest request){


		return getAllService.selectExcel(request);
}

}
