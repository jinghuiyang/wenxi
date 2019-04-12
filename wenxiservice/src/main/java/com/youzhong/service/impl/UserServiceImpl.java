package com.youzhong.service.impl;

import com.youzhong.dao.UserMapper;
import com.youzhong.entity.User;
import com.youzhong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectMes(User user) {
        return userMapper.selectMes(user);
    }
}
