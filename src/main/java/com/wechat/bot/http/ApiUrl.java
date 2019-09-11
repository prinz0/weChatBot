package com.wechat.bot.http;

/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-16
 */
public class ApiUrl {

    //微控API接口地址
    private static final String baseUrl = "http://api.gptouzia.com/";
    //开发者的服务器地址
    public static final String serviceUrl = "http://39.107.246.116:8080";
    //登录
    public static String loginUrl = baseUrl + "foreign/auth/login";
    //获取用户可用区域
    public static String getAreaUrl = baseUrl + "foreign/user/getArea";
    //获取二维码
    public static String scanQRCode = baseUrl + "foreign/message/scanNew";
    //配置全局回调地址
    public static String callBackUrl = baseUrl + "foreign/user/setUrl";
    //发送普通消息
    public static String sendMsg = baseUrl + "foreign/message/send";
    //获取微信群列表
    public static String getGroupList = baseUrl + "foreign/group/get";
    //通过好友添加申请
    public static String passAddFriends = baseUrl + "foreign/friends/passAddFriends";
    //发送名片
    public static String wacatCard = baseUrl + "foreign/message/wacatCard";
    //发送群邀请
    public static String invateMember = baseUrl + "foreign/group/invateMember";
    //群@
    public static String groupAt = baseUrl + "foreign/group/groupAt";



}
