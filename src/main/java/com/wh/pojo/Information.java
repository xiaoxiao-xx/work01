package com.wh.pojo;

import java.util.Date;

public class Information {
	private String state;
	private String travelNum;
	private String userName;
	private String userNum;
	private String destination;
	private String cause;
	private Date gmtGo;
	private Date gmtBack;
	private String stayDays;
	
	private String Persons;
	private String backMethod;
	private String payMethods;
	private Double payMoney;
	
	private String cityName;
	private String cityCode;
	private Double standard;
	
	private String costPayMethod;
	private String costPayMoney;

	private String backTimes;
	
	public String getStayDays() {
		return stayDays;
	}
	public void setStayDays(String stayDays) {
		this.stayDays = stayDays;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getPersons() {
		return Persons;
	}
	public void setPersons(String persons) {
		Persons = persons;
	}
	
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getTravelNum() {
		return travelNum;
	}
	public void setTravelNum(String travelNum) {
		this.travelNum = travelNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getGmtGo() {
		return gmtGo;
	}
	public void setGmtGo(Date gmtGo) {
		this.gmtGo = gmtGo;
	}
	public Date getGmtBack() {
		return gmtBack;
	}
	public void setGmtBack(Date gmtBack) {
		this.gmtBack = gmtBack;
	}
	public String getBackMethod() {
		return backMethod;
	}
	public void setBackMethod(String backMethod) {
		this.backMethod = backMethod;
	}
	public String getPayMethods() {
		return payMethods;
	}
	public void setPayMethods(String payMethods) {
		this.payMethods = payMethods;
	}
	public Double getStandard() {
		return standard;
	}
	public void setStandard(Double standard) {
		this.standard = standard;
	}
	public String getCostPayMethod() {
		return costPayMethod;
	}
	public void setCostPayMethod(String costPayMethod) {
		this.costPayMethod = costPayMethod;
	}
	public String getCostPayMoney() {
		return costPayMoney;
	}
	public void setCostPayMoney(String costPayMoney) {
		this.costPayMoney = costPayMoney;
	}
	@Override
	public String toString() {
		return "Information [state=" + state + ", travelNum=" + travelNum + ", userName=" + userName + ", userNum="
				+ userNum + ", destination=" + destination + ", cause=" + cause + ", gmtGo=" + gmtGo + ", gmtBack="
				+ gmtBack + ", stayDays=" + stayDays + ", Persons=" + Persons + ", backMethod=" + backMethod
				+ ", payMethods=" + payMethods + ", payMoney=" + payMoney + ", cityName=" + cityName + ", cityCode="
				+ cityCode + ", standard=" + standard + ", costPayMethod=" + costPayMethod + ", costPayMoney="
				+ costPayMoney + ", backTimes=" + backTimes + "]";
	}


	public void setBackTimes(String backTimes) {
		this.backTimes = backTimes;
	}

	public String getBackTimes() {
		return backTimes;
	}
}







