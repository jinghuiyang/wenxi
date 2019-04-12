package com.youzhong.controller;

import com.youzhong.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("weiboLogin")
    public String weiboLogin(String code){
        //微博apl上的放送的地址
     String url="https://api.weibo.com/oauth2/access_token" ;
        Map map=  new HashMap();
        //要封装的数据
     map.put("client_id","258427282");
     map.put("client_secret","599f156ca462200fb10dd987bb01702e");
     map.put("grant_type","authorization_code");
     map.put("code",code);
     map.put("redirect_uri","http://127.0.0.1:8080/login/weiboLogin");
        String json = HttpUtil.post(url, map);
        JSONObject jsonObject = JSONObject.fromObject(json);//将json转换成对象

        return "";
    }

}
