package com.youzhong.service.impl;

import com.youzhong.dao.OrderItemMapper;
import com.youzhong.entity.OrderItem;
import com.youzhong.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void insert(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }
}
