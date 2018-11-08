package com.wh.service;

import javax.servlet.http.HttpServletRequest;

public interface GetAllService {
	String getPage(HttpServletRequest request);

	String listTravel(HttpServletRequest request);

	String selectExcel(HttpServletRequest request);
}
