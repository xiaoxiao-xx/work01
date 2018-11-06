package com.wh.controller;

import com.wh.pojo.Information;
import com.wh.service.UpdateService;
import com.wh.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UpdateController {
	@Autowired
	private UpdateService updateService;
	
	@RequestMapping("update/travel")
	@ResponseBody
	public Result updateTravel(@RequestBody Information information){
		Result result = new Result();
		System.out.println(information);
		
		result.setStatus(0);
		result.setMessage("添加成功~");
		try {
			updateService.updateTravel(information);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(1);
			result.setMessage("添加失败~请稍后再试~");
			return result;
		}
		return result;
	}
	
	
}



