package com.youzhong.service.impl;

import com.youzhong.dao.UserMapper;
import com.youzhong.entity.User;
import com.youzhong.entity.UserExample;
import com.youzhong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public User selectuid(String id) {
        UserExample userExample = new UserExample();
        //判断是否存在这个对象
        userExample.createCriteria().andSocialUidEqualTo(id);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User buOpenid(Integer uid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()//
                .andIdEqualTo(uid);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0 && users != null) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public String selectUUUID(String uuid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()//
                .andUuidEqualTo(uuid);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0 && users != null) {
             if (users.get(0).getUuid().equals(uuid)){
                 return "ok";
             }
        }
        return "no";
    }
}
