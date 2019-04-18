package com.youzhong.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 封装商品，进入redis数据库
 */
public class Goods implements Serializable{
    private String id;//商品id
   private Integer zhuId;//表中数据id
   private Integer uid;
    private Integer num;//商品数量
    private BigDecimal price;//商品价格

    public Integer getZhuId() {
        return zhuId;
    }

    public void setZhuId(Integer zhuId) {
        this.zhuId = zhuId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
