package com.wh.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SelectNumService {
    List <String>selectNum(HttpServletRequest request);
}
