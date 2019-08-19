package com.wechat.bot.http;

/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-16
 */
public class ApiUrl {


    private static final String baseUrl = "http://api.gptouzia.com/";

    //登录
    public static String loginUrl = baseUrl + "foreign/auth/login";
    //获取用户可用区域
    public static String getAreaUrl = baseUrl + "foreign/user/getArea";
    //获取二维码
    public static String scanQRCode = baseUrl + "foreign/message/scanNew";



}
