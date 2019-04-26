package com.youzhong.service;

//@WebService//使用jws的标注WebService, 否则就不能被发布
public interface UserBindService {
    //public String bingUser(String tel,String password,String openid);
    public  String getUser(String tel,String password,String openid);
    public  String test(String a);

}
