package com.wh.service.impl;

import com.google.gson.Gson;
import com.wh.CostStandardConfig;
import com.wh.dao.TravelMapper;
import com.wh.pojo.Business;
import com.wh.pojo.vo.PageVO;
import com.wh.service.GetAllService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetALLServiceImpl implements GetAllService {
    private static final Integer NUM_OF_ONE_PAGE = 10;
    private static final String TRANSPORTATION_TYPE_COMP = "0";
    private static final String TRANSPORTATION_TYPE_OTHER = "1";
    private static final String STAY_TYPE_COMP = "0";
    private CostStandardConfig cs = new CostStandardConfig();
    private static final Gson GSON = new Gson();
    private static PageVO page = new PageVO();
    @Resource
    private TravelMapper  travelMapper;

    @Override
    public String getPage(HttpServletRequest request) {
        int totalRecords = travelMapper.countGetAll();
        int total = totalRecords / NUM_OF_ONE_PAGE;
        total = totalRecords % NUM_OF_ONE_PAGE == 0 ? total : total + 1;
        page = new PageVO(total + "", totalRecords + "", NUM_OF_ONE_PAGE + "");
        return GSON.toJson(page);

    }

    @Override
    public String listTravel(HttpServletRequest request) {

        String pn = request.getParameter("pn");
        int offst = (Integer.parseInt(pn) - 1) * NUM_OF_ONE_PAGE;
        int size = NUM_OF_ONE_PAGE;
        Map<String, Object> map = new HashMap<>();
        map.put("offst", offst);
        map.put("size", size);
        List<Business> list = travelMapper.selectByPage(map);
        int id=0;
        for (int i = 0; i <list.size() ; i++) {
            id =id+1;
           Business business = list.get(i);
           business.setId(id);
        }
        return GSON.toJson(list);
    }

    /**
     * 查询
     * @param request
     * @return
     */
    @Override
    public String selectExcel(HttpServletRequest request) {
        String value=request.getParameter("value");
        value="%"+value+"%";
        List<Business> list = travelMapper.selectExcel(value);
        return GSON.toJson(list);

    }
}
