package com.wh.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TravelUserT {
    private Long id;

    private String travelNum;

    private String userNum;

    private String userName;

    private Date gmtGo;

    private Date gmtBack;

    private String trasportationBack;

    private String trasportationGo;

    private BigDecimal costGo;

    private BigDecimal costBack;

    private BigDecimal cosStay;

    private String bookingTypeBack;

    private String bookingTypeGo;

    private String stayBookingType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTravelNum() {
        return travelNum;
    }

    public void setTravelNum(String travelNum) {
        this.travelNum = travelNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getTrasportationBack() {
        return trasportationBack;
    }

    public void setTrasportationBack(String trasportationBack) {
        this.trasportationBack = trasportationBack;
    }

    public String getTrasportationGo() {
        return trasportationGo;
    }

    public void setTrasportationGo(String trasportationGo) {
        this.trasportationGo = trasportationGo;
    }

    public BigDecimal getCostGo() {
        return costGo;
    }

    public void setCostGo(BigDecimal costGo) {
        this.costGo = costGo;
    }

    public BigDecimal getCostBack() {
        return costBack;
    }

    public void setCostBack(BigDecimal costBack) {
        this.costBack = costBack;
    }

    public BigDecimal getCosStay() {
        return cosStay;
    }

    public void setCosStay(BigDecimal cosStay) {
        this.cosStay = cosStay;
    }

    public String getBookingTypeBack() {
        return bookingTypeBack;
    }

    public void setBookingTypeBack(String bookingTypeBack) {
        this.bookingTypeBack = bookingTypeBack;
    }

    public String getBookingTypeGo() {
        return bookingTypeGo;
    }

    public void setBookingTypeGo(String bookingTypeGo) {
        this.bookingTypeGo = bookingTypeGo;
    }

    public String getStayBookingType() {
        return stayBookingType;
    }

    public void setStayBookingType(String stayBookingType) {
        this.stayBookingType = stayBookingType;
    }

    @Override
    public String toString() {
        return "TravelUserT{" +
                "id=" + id +
                ", travelNum='" + travelNum + '\'' +
                ", userNum='" + userNum + '\'' +
                ", userName='" + userName + '\'' +
                ", gmtGo=" + gmtGo +
                ", gmtBack=" + gmtBack +
                ", trasportationBack='" + trasportationBack + '\'' +
                ", trasportationGo='" + trasportationGo + '\'' +
                ", costGo=" + costGo +
                ", costBack=" + costBack +
                ", cosStay=" + cosStay +
                ", bookingTypeBack='" + bookingTypeBack + '\'' +
                ", bookingTypeGo='" + bookingTypeGo + '\'' +
                ", stayBookingType='" + stayBookingType + '\'' +
                '}';
    }
}