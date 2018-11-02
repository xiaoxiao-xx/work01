package com.wh.pojo.vo;

public class PageVO {
    //总页数
    private String total;
    //总数据条数
    private String totalRecords;
    //每页数据条数
    private String onePage;

    public PageVO() {
    }

    public PageVO(String total, String totalRecords, String onePage) {
        this.total = total;
        this.totalRecords = totalRecords;
        this.onePage = onePage;
    }

    public String getTotal() {
        return total;
    }

    public String getTotalRecords() {
        return totalRecords;
    }

    public String getOnePage() {
        return onePage;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setOnePage(String onePage) {
        this.onePage = onePage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageVO{");
        sb.append("total='").append(total).append('\'');
        sb.append(", totalRecords='").append(totalRecords).append('\'');
        sb.append(", onePage='").append(onePage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}