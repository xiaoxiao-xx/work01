package com.wh.pojo.vo;

import java.math.BigDecimal;

public class TravelCostVO {
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
    //回订票类型
    private String bookingTypeBack;
    //去订票类型
    private String bookingTypeGo;
    //住宿预定类型
    private String stayBookingType;
    //交通公司订购
    private BigDecimal trasportationComp;
    //交通其他订购
    private BigDecimal trasportationOther;
    //去费用
    private BigDecimal costGo;
    //回费用
    private BigDecimal costBack;
    //住宿费用
    private BigDecimal costStay;
    //住宿标准/天
    private BigDecimal stayOneDay;
    //住宿天数
    private String stayDays;
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

    public String getStayDays() {
        return stayDays;
    }

    public void setGoTimePoint(String goTimePoint) {
        this.goTimePoint = goTimePoint;
    }

    public void setStayDays(String stayDays) {
        this.stayDays = stayDays;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDepartment() {
        return department;
    }

    public String getCostDep() {
        return costDep;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getCause() {
        return cause;
    }

    public String getTrip() {
        return trip;
    }

    public String getGmtGo() {
        return gmtGo;
    }

    public String getGoTimePoint() {
        return goTimePoint;
    }

    public String getGmtBack() {
        return gmtBack;
    }

    public String getBackTimePoint() {
        return backTimePoint;
    }

    public BigDecimal getDays() {
        return days;
    }

    public String getBookingTypeBack() {
        return bookingTypeBack;
    }

    public String getBookingTypeGo() {
        return bookingTypeGo;
    }

    public String getStayBookingType() {
        return stayBookingType;
    }

    public BigDecimal getTrasportationComp() {
        return trasportationComp;
    }

    public BigDecimal getTrasportationOther() {
        return trasportationOther;
    }

    public BigDecimal getCostGo() {
        return costGo;
    }

    public BigDecimal getCostBack() {
        return costBack;
    }

    public BigDecimal getCostStay() {
        return costStay;
    }

    public BigDecimal getStayOneDay() {
        return stayOneDay;
    }

    public BigDecimal getStayStandard() {
        return stayStandard;
    }

    public BigDecimal getStayComp() {
        return stayComp;
    }

    public BigDecimal getStayOther() {
        return stayOther;
    }

    public BigDecimal getTrafficAllowanceOneDay() {
        return trafficAllowanceOneDay;
    }

    public BigDecimal getTrafficAllowanceStandard() {
        return trafficAllowanceStandard;
    }

    public BigDecimal getTrafficAllowanceReal() {
        return trafficAllowanceReal;
    }

    public BigDecimal getLifeAllowanceOneDay() {
        return lifeAllowanceOneDay;
    }

    public BigDecimal getLifeAllowanceStandard() {
        return lifeAllowanceStandard;
    }

    public BigDecimal getLifeAllowanceReal() {
        return lifeAllowanceReal;
    }

    public BigDecimal getSalaryAllowance() {
        return salaryAllowance;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public String getMarks() {
        return marks;
    }

    public TravelCostVO() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCostDep(String costDep) {
        this.costDep = costDep;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public void setGmtGo(String gmtGo) {
        this.gmtGo = gmtGo;
    }

    public void setGotimePoint(String gotimePoint) {
        this.goTimePoint = gotimePoint;
    }

    public void setGmtBack(String gmtBack) {
        this.gmtBack = gmtBack;
    }

    public void setBackTimePoint(String backTimePoint) {
        this.backTimePoint = backTimePoint;
    }

    public void setDays(BigDecimal days) {
        this.days = days;
    }

    public void setBookingTypeBack(String bookingTypeBack) {
        this.bookingTypeBack = bookingTypeBack;
    }

    public void setBookingTypeGo(String bookingTypeGo) {
        this.bookingTypeGo = bookingTypeGo;
    }

    public void setStayBookingType(String stayBookingType) {
        this.stayBookingType = stayBookingType;
    }

    public void setTrasportationComp(BigDecimal trasportationComp) {
        this.trasportationComp = trasportationComp;
    }

    public void setTrasportationOther(BigDecimal trasportationOther) {
        this.trasportationOther = trasportationOther;
    }

    public void setCostGo(BigDecimal costGo) {
        this.costGo = costGo;
    }

    public void setCostBack(BigDecimal costBack) {
        this.costBack = costBack;
    }

    public void setCostStay(BigDecimal costStay) {
        this.costStay = costStay;
    }

    public void setStayOneDay(BigDecimal stayOneDay) {
        this.stayOneDay = stayOneDay;
    }

    public void setStayStandard(BigDecimal stayStandard) {
        this.stayStandard = stayStandard;
    }

    public void setStayComp(BigDecimal stayComp) {
        this.stayComp = stayComp;
    }

    public void setStayOther(BigDecimal stayOther) {
        this.stayOther = stayOther;
    }

    public void setTrafficAllowanceOneDay(BigDecimal trafficAllowanceOneDay) {
        this.trafficAllowanceOneDay = trafficAllowanceOneDay;
    }

    public void setTrafficAllowanceStandard(BigDecimal trafficAllowanceStandard) {
        this.trafficAllowanceStandard = trafficAllowanceStandard;
    }

    public void setTrafficAllowanceReal(BigDecimal trafficAllowanceReal) {
        this.trafficAllowanceReal = trafficAllowanceReal;
    }

    public void setLifeAllowanceOneDay(BigDecimal lifeAllowanceOneDay) {
        this.lifeAllowanceOneDay = lifeAllowanceOneDay;
    }

    public void setLifeAllowanceStandard(BigDecimal lifeAllowanceStandard) {
        this.lifeAllowanceStandard = lifeAllowanceStandard;
    }

    public void setLifeAllowanceReal(BigDecimal lifeAllowanceReal) {
        this.lifeAllowanceReal = lifeAllowanceReal;
    }

    public void setSalaryAllowance(BigDecimal salaryAllowance) {
        this.salaryAllowance = salaryAllowance;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TravelCostVO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append(", costDep='").append(costDep).append('\'');
        sb.append(", userLevel='").append(userLevel).append('\'');
        sb.append(", cause='").append(cause).append('\'');
        sb.append(", trip='").append(trip).append('\'');
        sb.append(", gmtGo='").append(gmtGo).append('\'');
        sb.append(", goTimePoint='").append(goTimePoint).append('\'');
        sb.append(", gmtBack='").append(gmtBack).append('\'');
        sb.append(", backTimePoint='").append(backTimePoint).append('\'');
        sb.append(", days=").append(days);
        sb.append(", bookingTypeBack='").append(bookingTypeBack).append('\'');
        sb.append(", bookingTypeGo='").append(bookingTypeGo).append('\'');
        sb.append(", stayBookingType='").append(stayBookingType).append('\'');
        sb.append(", trasportationComp=").append(trasportationComp);
        sb.append(", trasportationOther=").append(trasportationOther);
        sb.append(", costGo=").append(costGo);
        sb.append(", costBack=").append(costBack);
        sb.append(", costStay=").append(costStay);
        sb.append(", stayOneDay=").append(stayOneDay);
        sb.append(", stayDays='").append(stayDays).append('\'');
        sb.append(", stayStandard=").append(stayStandard);
        sb.append(", stayComp=").append(stayComp);
        sb.append(", stayOther=").append(stayOther);
        sb.append(", trafficAllowanceOneDay=").append(trafficAllowanceOneDay);
        sb.append(", trafficAllowanceStandard=").append(trafficAllowanceStandard);
        sb.append(", trafficAllowanceReal=").append(trafficAllowanceReal);
        sb.append(", lifeAllowanceOneDay=").append(lifeAllowanceOneDay);
        sb.append(", lifeAllowanceStandard=").append(lifeAllowanceStandard);
        sb.append(", lifeAllowanceReal=").append(lifeAllowanceReal);
        sb.append(", salaryAllowance=").append(salaryAllowance);
        sb.append(", subTotal=").append(subTotal);
        sb.append(", marks='").append(marks).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
