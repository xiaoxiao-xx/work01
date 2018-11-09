package com.wh.service.impl;

import com.wh.dao.UserTMapper;
import com.wh.service.SelectNumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SelectNumImpl implements SelectNumService {

    @Resource
     private UserTMapper userTMapper;

    @Override
    public List<String> selectNum(HttpServletRequest request) {
        String name =request.getParameter("name");
       List<String>   namelist=  userTMapper.selectNum(name);
       return namelist;
    }
}
