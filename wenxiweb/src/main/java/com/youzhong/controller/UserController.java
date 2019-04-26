package com.youzhong.controller;

import com.youzhong.entity.User;
import com.youzhong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register")
    public Integer register(User user) {
        if ("".equals(user.getMail()) || "".equals(user.getTel()) || "".equals(user.getPassword())) {
            return 0;
        } else {
            return userService.register(user);//注册

        }

    }

    @RequestMapping("login")
    public Integer login(User user, HttpSession session) {
        if ("".equals(user.getTel()) || "".equals(user.getPassword())) {
            return 0;
        } else {
              User login = userService.selectMes(user);
            if (login != null) {
                session.setAttribute("user", login);
                return 1;
            }

            return 2;
        }
    }

}
