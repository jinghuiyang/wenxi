package com.youzhong.controller;

import com.youzhong.entity.Goods;
import com.youzhong.entity.Order;
import com.youzhong.entity.OrderItem;
import com.youzhong.entity.User;
import com.youzhong.service.GoodsService;
import com.youzhong.service.OrderItemService;
import com.youzhong.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 订单详情
 */

@RequestMapping("goods")
@Controller
public class GoodsMesController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;


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
            //一个用户只生成一个订单，但是购买的商品写进商品订单表中
            Order order = new Order();
            order.setCreatetime(new Date());//订单生成时间
            order.setUid(user.getId());//谁下的订单
            order.setTotalprice(new BigDecimal(33));//订单金额
            order.setStatus("0");
            orderService.insert(order);//将用户下的订单加入数据库中（订单只有一个）

            Set<Goods> objects = goodsService.allValue(user.getId());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderid(order.getOrdernum());//设置订单的id（记录来自哪个订单）
            for (Goods goods : objects) {
                //需要将用户的商品信息分开（记录商品的状态）
                orderItem.setProid(goods.getId());//商品id
                orderItem.setPronum(goods.getNum());
                orderItemService.insert(orderItem);
            }
        }
        goodsService.delete(user.getId());//清空购物车,清空radis数据库这个用户的数据
        return "ok";
    }
}
