package com.youzhong.controller;

import com.youzhong.service.UserService;
import com.youzhong.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import weibo4j.Users;
import weibo4j.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 抓取第三方登陆的信息
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("weiboLogin")
    public void weiboLogin(String code, Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        //微博apl上的放送的地址
        String url = "https://api.weibo.com/oauth2/access_token";
        Map map = new HashMap();
        //要封装的数据
        map.put("client_id", "258427282");
        map.put("client_secret", "599f156ca462200fb10dd987bb01702e");
        map.put("grant_type", "authorization_code");
        map.put("code", code);
        map.put("redirect_uri", "http://127.0.0.1:8080/login/weiboLogin");
        //工具类将数据带回
        String json = HttpUtil.post(url, map);
        //将数据解析成对象
        JSONObject jsonObject = JSONObject.fromObject(json);//将json转换成对象
        //可以通过对象将数据获取到，网站的token
        String access_token = jsonObject.get("access_token").toString();

        String uid = jsonObject.get("uid").toString();//得到uid

        Users um = new Users(access_token);
        try {
            //提供的工具找到对应的对象
            User user = um.showUserById(uid);
            //数据库比对是否有这个用户,根据uid去查找用户
            System.out.println(user.toString());
            com.youzhong.entity.User login = userService.selectuid(user.getId());
            if (login != null) {//如果有重定向到主页面
                session.setAttribute("user", login);
                response.sendRedirect("/index.jsp");

            } else {//没有请求转发到注册页面
                session.setAttribute("wenbo", user);
                request.getRequestDispatcher("/index/register").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("userLogin")
    @ResponseBody
    public com.youzhong.entity.User userLogin(HttpSession session){
        com.youzhong.entity.User user = (com.youzhong.entity.User) session.getAttribute("user");
        if(user!=null){//判断session是否空
            return user;
        }else {//不存在
            com.youzhong.entity.User user1 = new com.youzhong.entity.User();
            user1.setId(1);
            return user1;
        }
    }

}
