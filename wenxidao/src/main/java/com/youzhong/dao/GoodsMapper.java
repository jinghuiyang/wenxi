package com.youzhong.dao;

import com.youzhong.entity.Goods;
import org.apache.ibatis.annotations.Select;

public interface GoodsMapper {
    @Select("insert into t_goods values(null,#{id},#{uid},#{num},#{price})")
    void insert(Goods goods1);
}
