package com.youzhong.service.impl;

import com.youzhong.dao.GoodsMapper;
import com.youzhong.entity.Goods;
import com.youzhong.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * redis数据库存储时key是String
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public String getgoodsId(Integer id, String goodsId) {
        //得到radis数据库单个商品信息
        String num = (String) redisTemplate.opsForHash().get(id + "", goodsId);
        return num;
    }

    @Override
    public void insert(Integer id, String goodsId, Integer goods1) {
        //先删除key
       // redisTemplate.opsForHash().delete(id+"",goodsId+"");
        /* redisTemplate.opsForHash().put(id+"",goodsId,goods1+"");*/
        HashMap<Object, Object> map = new HashMap<>();
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setNum(goods1);
        map.put(goods1 + "", goods);
        redisTemplate.opsForHash().putAll(id + "", map);
    }

    @Override
    public Set<Goods> allValue(Integer id) {
        //得到数据库这个用户的所有信息(这个拿到的只是小key，存储时存的是对象)
        Set<Object> keys = redisTemplate.opsForHash().keys(id + "");
        HashSet<Goods> set = new HashSet<>();
        //这个是根据对象的key去找每个商品
        if (keys.size() > 0) {
            for(Object key:keys){
                Goods  goods = (Goods) redisTemplate.opsForHash().get(id + "", key.toString());
                set.add(goods);
            }
        }
        return set;
    }

    @Override
    public void insertMySQL(Goods goods1) {
        goodsMapper.insert(goods1);
    }

    @Override
    public void delete(Integer id) {
        Set<Object> keys = redisTemplate.opsForHash().keys(id+"");
        for(Object o:keys){//需要将radis数据清空,这个方法需要是不定参数列表
            //循环的话也不需要考虑有几个，redis数据库操作比较快
            redisTemplate.opsForHash().delete(id+"",o.toString());
        }

    }


}
