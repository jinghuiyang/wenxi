package com.youzhong.entity;

public class OrderItem {
    private Integer id;

    private String proid;

    private Integer pronum;

    private Integer orderid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public Integer getPronum() {
        return pronum;
    }

    public void setPronum(Integer pronum) {
        this.pronum = pronum;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}