package com.youzhong.service;

import com.youzhong.entity.Goods;

import java.util.Set;

public interface GoodsService {
    String getgoodsId(Integer id, String goodsId);

    void insert(Integer id, String goodsId, Integer goods1);
    Set<Goods> allValue(Integer id);

    void insertMySQL(Goods goods1);
}
