package com.youzhong.service;

import com.youzhong.entity.Order;

public interface OrderService {
    void insert(Order order);

    Order byUserId(Integer id);

    void update(Order order);

    Order OrderByDesc();
}
