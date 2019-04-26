package com.youzhong.service.impl;

import com.youzhong.dao.UserMapper;
import com.youzhong.entity.User;
import com.youzhong.entity.UserExample;
import com.youzhong.service.UserBindService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class UserBindServiceImpl implements UserBindService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUser(String tel, String password, String openid) {
        System.out.println(tel);
        System.out.println(password);
        System.out.println(openid);
        UserExample userExample = new UserExample();
        userExample.createCriteria()//
                .andTelEqualTo(tel)//
                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0 && users != null) {//判断有没有这个记录
            users.get(0).setOpenid(openid);
            userMapper.updateByPrimaryKey(users.get(0));
            return "success";
        } else {
            return "no";
        }

    }

    @Override
    public String test(String a) {
        return "ok";
    }
}
