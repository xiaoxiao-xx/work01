package com.wh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TravelCostService {
    String getPage(HttpServletRequest request);

    String listTravel_cost(HttpServletRequest request);

    void exportInfo(HttpServletRequest request, HttpServletResponse response);
}
