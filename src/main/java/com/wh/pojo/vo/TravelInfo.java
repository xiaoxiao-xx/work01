package com.wh.pojo.vo;

import java.math.BigDecimal;

public class TravelInfo {
    //序号
    private String id;
    //姓名
    private String userName;
    //部门
    private String department;
    //费用所属部门
    private String costDep;
    //人员级别
    private String userLevel;
    //出差目的
    private String cause;
    //出差行程
    private String trip;
    //开始日期
    private String gmtGo;
    //出发时间点
    private String goTimePoint;
    //结束日期
    private String gmtBack;
    //结束时间点
    private String backTimePoint;
    //天数
    private BigDecimal days;
    //交通公司订购
    private BigDecimal trasportationComp;
    //交通其他订购
    private BigDecimal trasportationOther;
    //住宿标准/天
    private BigDecimal stayOneDay;
    //住宿标准
    private BigDecimal stayStandard;
    //住宿公司订购
    private BigDecimal stayComp;
    //住宿其他订购
    private BigDecimal stayOther;
    //交通补贴标准/天
    private BigDecimal trafficAllowanceOneDay;
    //交通补贴标准
    private BigDecimal trafficAllowanceStandard;
    //交通补贴实报
    private BigDecimal trafficAllowanceReal;
    //生活补贴标准/天
    private BigDecimal lifeAllowanceOneDay;
    //生活补贴标准
    private BigDecimal lifeAllowanceStandard;
    //生活补贴实报
    private BigDecimal lifeAllowanceReal;
    //工资发放补贴
    private BigDecimal salaryAllowance;
    //个人小计
    private BigDecimal subTotal;
    //备注
    private String marks;

    public TravelInfo() {
    }

    public TravelInfo(String id, String userName, String department, String costDep, String userLevel, String cause, String trip, String gmtGo, String goTimePoint, String gmtBack, String backTimePoint, BigDecimal days, BigDecimal trasportationComp, BigDecimal trasportationOther, BigDecimal stayOneDay, BigDecimal stayStandard, BigDecimal stayComp, BigDecimal stayOther, BigDecimal trafficAllowanceOneDay, BigDecimal trafficAllowanceStandard, BigDecimal trafficAllowanceReal, BigDecimal lifeAllowanceOneDay, BigDecimal lifeAllowanceStandard, BigDecimal lifeAllowanceReal, BigDecimal salaryAllowance, BigDecimal subTotal, String marks) {
        this.id = id;
        this.userName = userName;
        this.department = department;
        this.costDep = costDep;
        this.userLevel = userLevel;
        this.cause = cause;
        this.trip = trip;
        this.gmtGo = gmtGo;
        this.goTimePoint = goTimePoint;
        this.gmtBack = gmtBack;
        this.backTimePoint = backTimePoint;
        this.days = days;
        this.trasportationComp = trasportationComp;
        this.trasportationOther = trasportationOther;
        this.stayOneDay = stayOneDay;
        this.stayStandard = stayStandard;
        this.stayComp = stayComp;
        this.stayOther = stayOther;
        this.trafficAllowanceOneDay = trafficAllowanceOneDay;
        this.trafficAllowanceStandard = trafficAllowanceStandard;
        this.trafficAllowanceReal = trafficAllowanceReal;
        this.lifeAllowanceOneDay = lifeAllowanceOneDay;
        this.lifeAllowanceStandard = lifeAllowanceStandard;
        this.lifeAllowanceReal = lifeAllowanceReal;
        this.salaryAllowance = salaryAllowance;
        this.subTotal = subTotal;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCostDep() {
        return costDep;
    }

    public void setCostDep(String costDep) {
        this.costDep = costDep;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getGmtGo() {
        return gmtGo;
    }

    public void setGmtGo(String gmtGo) {
        this.gmtGo = gmtGo;
    }

    public String getGoTimePoint() {
        return goTimePoint;
    }

    public void setGoTimePoint(String goTimePoint) {
        this.goTimePoint = goTimePoint;
    }

    public String getGmtBack() {
        return gmtBack;
    }

    public void setGmtBack(String gmtBack) {
        this.gmtBack = gmtBack;
    }

    public String getBackTimePoint() {
        return backTimePoint;
    }

    public void setBackTimePoint(String backTimePoint) {
        this.backTimePoint = backTimePoint;
    }

    public BigDecimal getDays() {
        return days;
    }

    public void setDays(BigDecimal days) {
        this.days = days;
    }

    public BigDecimal getTrasportationComp() {
        return trasportationComp;
    }

    public void setTrasportationComp(BigDecimal trasportationComp) {
        this.trasportationComp = trasportationComp;
    }

    public BigDecimal getTrasportationOther() {
        return trasportationOther;
    }

    public void setTrasportationOther(BigDecimal trasportationOther) {
        this.trasportationOther = trasportationOther;
    }

    public BigDecimal getStayOneDay() {
        return stayOneDay;
    }

    public void setStayOneDay(BigDecimal stayOneDay) {
        this.stayOneDay = stayOneDay;
    }

    public BigDecimal getStayStandard() {
        return stayStandard;
    }

    public void setStayStandard(BigDecimal stayStandard) {
        this.stayStandard = stayStandard;
    }

    public BigDecimal getStayComp() {
        return stayComp;
    }

    public void setStayComp(BigDecimal stayComp) {
        this.stayComp = stayComp;
    }

    public BigDecimal getStayOther() {
        return stayOther;
    }

    public void setStayOther(BigDecimal stayOther) {
        this.stayOther = stayOther;
    }

    public BigDecimal getTrafficAllowanceOneDay() {
        return trafficAllowanceOneDay;
    }

    public void setTrafficAllowanceOneDay(BigDecimal trafficAllowanceOneDay) {
        this.trafficAllowanceOneDay = trafficAllowanceOneDay;
    }

    public BigDecimal getTrafficAllowanceStandard() {
        return trafficAllowanceStandard;
    }

    public void setTrafficAllowanceStandard(BigDecimal trafficAllowanceStandard) {
        this.trafficAllowanceStandard = trafficAllowanceStandard;
    }

    public BigDecimal getTrafficAllowanceReal() {
        return trafficAllowanceReal;
    }

    public void setTrafficAllowanceReal(BigDecimal trafficAllowanceReal) {
        this.trafficAllowanceReal = trafficAllowanceReal;
    }

    public BigDecimal getLifeAllowanceOneDay() {
        return lifeAllowanceOneDay;
    }

    public void setLifeAllowanceOneDay(BigDecimal lifeAllowanceOneDay) {
        this.lifeAllowanceOneDay = lifeAllowanceOneDay;
    }

    public BigDecimal getLifeAllowanceStandard() {
        return lifeAllowanceStandard;
    }

    public void setLifeAllowanceStandard(BigDecimal lifeAllowanceStandard) {
        this.lifeAllowanceStandard = lifeAllowanceStandard;
    }

    public BigDecimal getLifeAllowanceReal() {
        return lifeAllowanceReal;
    }

    public void setLifeAllowanceReal(BigDecimal lifeAllowanceReal) {
        this.lifeAllowanceReal = lifeAllowanceReal;
    }

    public BigDecimal getSalaryAllowance() {
        return salaryAllowance;
    }

    public void setSalaryAllowance(BigDecimal salaryAllowance) {
        this.salaryAllowance = salaryAllowance;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
