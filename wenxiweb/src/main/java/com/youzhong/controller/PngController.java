package com.youzhong.controller;

import com.youzhong.service.UserService;
import com.youzhong.util.QrcodenoImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 用于生成二维码图片
 */
@Controller
@RequestMapping("png")
public class PngController {

    @Autowired
    private UserService userService;

    @RequestMapping("ma")
    public String ma(HttpServletRequest request) {//生成二维码

        // String uuid = UUID.randomUUID().toString().substring(0, 5);
        String uuid = UUID.randomUUID().toString();
        String context = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3062a29394cca3bd&redirect_uri=http://yangjinghui.jinghuiyang.top/png/png?uuid=" + uuid + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

        System.out.println(context);
        String path = "D:/wordspace/wenxi/wenxiweb/src/main/webapp/imge/"+uuid+".png";
        QrcodenoImg.createQRCode(context, path);
        //model.addAttribute("uuid", uuid);

        request.setAttribute("uuid", uuid);
        return "imge/imge";
    }

    @RequestMapping("png")
    @ResponseBody
    public String png(String uuid, String code) {//回调地址
        System.out.println(uuid);
        System.out.println(code);
        return "success";
    }

    @RequestMapping("login")
    public String login(String uuid) {//判断用户是否扫码登陆完成
        return userService.selectUUUID(uuid);
    }

}
