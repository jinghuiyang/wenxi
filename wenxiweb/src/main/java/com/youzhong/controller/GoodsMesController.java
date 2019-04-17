package com.youzhong.controller;

import com.youzhong.entity.Goods;
import com.youzhong.entity.User;
import com.youzhong.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 订单详情
 */

@RequestMapping("goods")
@Controller
public class GoodsMesController {
    @Autowired
    private GoodsService goodsService;


    @RequestMapping("mes")
    public String mes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {//判断用户是否登陆
            Set<Goods> objects = goodsService.allValue(user.getId());
            int num = 0;
            for (Goods goods : objects) {
                num += goods.getNum().intValue();
            }
            //数据库存储的这个用户的所有信息
            model.addAttribute("gmes", objects);
            model.addAttribute("num", num);
        }

        return "order/goodsMes";
    }

    @RequestMapping("submit")
    @ResponseBody
    public String submit(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Goods goods1 = new Goods();
            goods1.setUid(user.getId());
            goods1.setPrice(33);

            Set<Goods> objects = goodsService.allValue(user.getId());
            for(Goods goods:objects){
              goods1.setId(goods.getId());//商品id
                goods1.setNum(goods.getNum());
                goodsService.insertMySQL(goods1);
            }
        }
        return "ok";
    }
}
