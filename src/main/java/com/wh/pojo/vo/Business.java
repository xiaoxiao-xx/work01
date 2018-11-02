package com.wh.pojo.vo;

import java.util.Date;

public class Business {

    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门
     */
    private String department;
    /**
     * 费用所属部门
     */
    private String departmentcost;
    /**
     * 人员级别
     */
    private String stafflevel;
    /**
     * 目的
     */
    private String purpose;
    /**
     * 出差行程
     */
    private String trip;
    /**
     * 开始时间
     */
    private Date starttime;
    /**
     * 出发时间点
     */
    private String goff;
    /**
     * 结束时间
     */
    private Date endtime;

    //结束时间点
    private String endff;

    /**
     * 累计时间(天)
     */
    private String days;
    /**
     * 公司订购
     */
    private String companyorder;
    /**
     * 其它订购
     */
    private String otherorder;
    /**
     * 标准/天
     */
    private String travelstandard;
    /**
     * 住宿标准
     */
    private String livestandard;
    /**
     * 公司订
     */
    private String companyset;
    /**
     * 个人订
     */
    private String personalset;
    /**
     * 标准/天
     */
    private String trafficperday;
    /**
     * 补贴标准
     */
    private String trafficreality;
    /**
     * 实报
     */
    private String trafficreal;
    /**
     * 标准/天
     */
    private String liveperday;
    /**
     * 生活补贴标准
     */
    private String livereality;
    /**
     * 生活实报
     */
    private String livereal;
    /**
     * 工资发放补贴
     */
    private String subsidy;
    /**
     * 个人小计
     */
    private String subplan;
    /**
     * 备注
     */
    private String remarks;

    public Business() {

    }

    public Business(Integer id, String name, String department, String departmentcost, String stafflevel, String purpose, String trip, Date starttime, String goff, Date endtime, String endff, String days, String companyorder, String otherorder, String travelstandard, String livestandard, String companyset, String personalset, String trafficperday, String trafficreality, String trafficreal, String liveperday, String livereality, String livereal, String subsidy, String subplan, String remarks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.departmentcost = departmentcost;
        this.stafflevel = stafflevel;
        this.purpose = purpose;
        this.trip = trip;
        this.starttime = starttime;
        this.goff = goff;
        this.endtime = endtime;
        this.endff = endff;
        this.days = days;
        this.companyorder = companyorder;
        this.otherorder = otherorder;
        this.travelstandard = travelstandard;
        this.livestandard = livestandard;
        this.companyset = companyset;
        this.personalset = personalset;
        this.trafficperday = trafficperday;
        this.trafficreality = trafficreality;
        this.trafficreal = trafficreal;
        this.liveperday = liveperday;
        this.livereality = livereality;
        this.livereal = livereal;
        this.subsidy = subsidy;
        this.subplan = subplan;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentcost() {
        return departmentcost;
    }

    public void setDepartmentcost(String departmentcost) {
        this.departmentcost = departmentcost;
    }

    public String getStafflevel() {
        return stafflevel;
    }

    public void setStafflevel(String stafflevel) {
        this.stafflevel = stafflevel;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getGoff() {
        return goff;
    }

    public void setGoff(String goff) {
        this.goff = goff;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }


    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCompanyorder() {
        return companyorder;
    }

    public void setCompanyorder(String companyorder) {
        this.companyorder = companyorder;
    }

    public String getOtherorder() {
        return otherorder;
    }

    public void setOtherorder(String otherorder) {
        this.otherorder = otherorder;
    }

    public String getTravelstandard() {
        return travelstandard;
    }

    public void setTravelstandard(String travelstandard) {
        this.travelstandard = travelstandard;
    }

    public String getLivestandard() {
        return livestandard;
    }

    public void setLivestandard(String livestandard) {
        this.livestandard = livestandard;
    }

    public String getCompanyset() {
        return companyset;
    }

    public void setCompanyset(String companyset) {
        this.companyset = companyset;
    }

    public String getPersonalset() {
        return personalset;
    }

    public void setPersonalset(String personalset) {
        this.personalset = personalset;
    }

    public String getTrafficperday() {
        return trafficperday;
    }

    public void setTrafficperday(String trafficperday) {
        this.trafficperday = trafficperday;
    }

    public String getTrafficreality() {
        return trafficreality;
    }

    public void setTrafficreality(String trafficreality) {
        this.trafficreality = trafficreality;
    }

    public String getTrafficreal() {
        return trafficreal;
    }

    public void setTrafficreal(String trafficreal) {
        this.trafficreal = trafficreal;
    }

    public String getLiveperday() {
        return liveperday;
    }

    public void setLiveperday(String liveperday) {
        this.liveperday = liveperday;
    }

    public String getLivereality() {
        return livereality;
    }

    public void setLivereality(String livereality) {
        this.livereality = livereality;
    }

    public String getLivereal() {
        return livereal;
    }

    public void setLivereal(String livereal) {
        this.livereal = livereal;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getSubplan() {
        return subplan;
    }

    public void setSubplan(String subplan) {
        this.subplan = subplan;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}