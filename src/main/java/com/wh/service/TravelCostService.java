package com.wh.service;

import javax.servlet.http.HttpServletRequest;

public interface TravelCostService {
    String getPage(HttpServletRequest request);

    String listTravel_cost(HttpServletRequest request);

    String exportInfo(HttpServletRequest request);
}
