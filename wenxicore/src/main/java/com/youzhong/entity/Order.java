package com.youzhong.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer ordernum;

    private Integer uid;

    private Date createtime;

    private BigDecimal totalprice;

    private String status;

    private String paytype;

    private Integer payid;

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }
}