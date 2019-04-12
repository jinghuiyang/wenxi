package com.youzhong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于页面跳转
 */

@RequestMapping("index")
@Controller
public class IndexController {

    @RequestMapping("register")//注册跳转
    public String register() {
        return "login/register";
    }

    @RequestMapping("login")
    public String login() {//登陆页面
        return "login/login";
    }
}
