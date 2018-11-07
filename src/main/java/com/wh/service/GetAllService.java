package com.wh.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wh.dao.TravelMapper;
import com.wh.pojo.Business;
@Service
public class GetAllService {
	@Autowired
	private TravelMapper travelMapper;
	
	public List<Business> selectAll(){
		
		List<Business> all = travelMapper.selectAll();
		return all;
	}
}
