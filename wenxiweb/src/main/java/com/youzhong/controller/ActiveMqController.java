package com.youzhong.controller;

import com.youzhong.service.impl.MySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("active")
public class ActiveMqController {

      @Autowired
    private MySender mySender;

    @RequestMapping("send")
    @ResponseBody
    public String send(){
        mySender.sendTextMes("测试","添加成功");
      return "success";
    }

}
