package com.youzhong.controller;

import com.youzhong.entity.Goods;
import com.youzhong.entity.User;
import com.youzhong.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 添加购物车页面
 */
@RequestMapping("cart")
@Controller
public class CartController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("a")
    public String a(Goods goods) {
        goodsService.insert(3, goods.getId(), goods.getNum());
        return "";
    }

    @RequestMapping("b")
    @ResponseBody
    public Set<Goods> b(Integer id, @RequestParam(required = false, defaultValue = "") String bid) {
        //String getgoodsId = goodsService.getgoodsId(id, bid);
        Set<Goods> objects = goodsService.allValue(id);
        return objects;

    }

    @RequestMapping("insertGoods")
    @ResponseBody
    public String insertGoods(HttpSession session, HttpServletRequest request, Goods goods) {//登陆直接添加
        User user = (User) session.getAttribute("user");
        Cookie[] cookies = request.getCookies();
        boolean flag = false;//判断是否有coTokie
        String num = "";
        if (cookies != null) {//判断cookie有没有数据
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                //判断名字是否一致
                if (goods.getId().equals(name)) {
                    flag = true;
                    num = cookies[i].getValue();
                    break;
                }

            }
        }

        if (flag) {//判断客户端是否有cookie
            int radisNum = 0;
            // String count = goodsService.getgoodsId(user.getId(), goods.getId());
            Set<Goods> objects = goodsService.allValue(user.getId());
            boolean result = false;
            for (Goods goods1 : objects) {
                if (goods1.getId().equals(goods.getId())) {
                    radisNum = goods1.getNum();
                    result = true;
                    break;
                }
            }
            //新数据
            int xin = goods.getNum().intValue();
            //客户端
            int ke = Integer.parseInt(num);
            //数据库
            int newNum = 0;
            if (radisNum != 0) {
                newNum += xin + ke + radisNum;
            } else {
                newNum += xin + ke;
            }

            if (result) {//判断redis是否有数据(加上新的数量和客户端的数量)
                goodsService.insert(user.getId(), goods.getId(), newNum);
            } else {//不等于空（加上数据库和客户端和新增的 ）
                goodsService.insert(user.getId(), goods.getId(), newNum);
            }
        } else {//客户端没有cookie
            int radisNum = 0;
            // String count = goodsService.getgoodsId(user.getId(), goods.getId());
            Set<Goods> objects = goodsService.allValue(user.getId());
            boolean result = false;
            for (Goods goods1 : objects) {
                if (goods1.getId().equals(goods.getId())) {
                    radisNum = goods1.getNum();
                    result = true;
                    break;
                }
            }
            if (result) {//判断redis是否有数据(此时客户端没有数据)，加数据库和新增的
                int r = goods.getNum().intValue();
                if (radisNum != 0) {
                    r += radisNum;
                }

                goodsService.insert(user.getId(), goods.getId(), r);
            } else {
                goodsService.insert(user.getId(), goods.getId(), goods.getNum());
            }
        }
        System.out.println(goods);

        return "ok";
    }

}
