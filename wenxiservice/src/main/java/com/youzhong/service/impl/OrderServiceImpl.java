package com.youzhong.service.impl;

import com.youzhong.dao.OrderMapper;
import com.youzhong.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
