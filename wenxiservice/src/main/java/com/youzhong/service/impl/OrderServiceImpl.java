package com.youzhong.service.impl;

import com.youzhong.dao.OrderMapper;
import com.youzhong.entity.Order;
import com.youzhong.entity.OrderExample;
import com.youzhong.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用于用户生成订单表
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insert(com.youzhong.entity.Order order) {
        orderMapper.insert(order);
    }

    @Override
    public Order byUserId(Integer id) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("createtime desc");//将订单根据时间倒序
        orderExample.createCriteria().andUidEqualTo(id);//加上用户id
        List<Order> orders = orderMapper.selectByExample(orderExample);
        if (orders.size() > 0 && orders != null) {
            return orders.get(0);
        }
        return null;
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public Order OrderByDesc() {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("createtime desc");//将订单根据时间倒序

        List<Order> orders = orderMapper.selectByExample(orderExample);
        if (orders.size() > 0 && orders != null) {
            return orders.get(0);
        }
        return null;
    }
}
