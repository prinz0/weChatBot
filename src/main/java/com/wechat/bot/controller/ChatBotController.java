package com.wechat.bot.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.wechat.bot.entity.AreaBean;
import com.wechat.bot.entity.BaseBean;
import com.wechat.bot.http.ApiUrl;
import com.wechat.bot.http.HttpUtils;
import com.wechat.bot.util.Constant;
import com.wechat.bot.util.GsonUtil;
import com.wechat.bot.util.MD5Util;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mr.li
 * @Desc： 机器人自动化模块
 * @Time: 2019-08-16
 */
@Controller
@RequestMapping("/bot")
public class ChatBotController {


    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();


    @RequestMapping("/home")
    @ResponseBody
    public String home(){
        return "1111";
    }
    /**
     * 开发者登录
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String phone, @RequestParam String password) {
        //拼装接口参数
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        //发送Post请求
        String strResult = HttpUtils.doPost(ApiUrl.loginUrl, params);
        BaseBean baseModel = GsonUtil.GsonToBean(strResult, BaseBean.class);
        //获取Apikey
        if (baseModel.getCode() == 1)
            session.setAttribute("apikey", JSONObject.parseObject(strResult).getJSONObject("data").getString("apikey"));

        return strResult;
    }

    //获取用户可用区域
    @RequestMapping(path = "/getArea", method = RequestMethod.POST)
    @ResponseBody
    public String getArea(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //组装headers头部
        Constant.apikey = (String) session.getAttribute("apikey");
        Constant.hswebtime = new Date().getTime() / 1000 + "_" + UUID.randomUUID().toString().replace("-", "");
        Constant.token = MD5Util.MD5(Constant.hswebtime + Constant.KEY);//第二个字段是开发者秘钥
        String res = HttpUtils.doPost(ApiUrl.getAreaUrl, new HashMap<>());
        BaseBean<List<AreaBean>> model = GsonUtil.GsonToBean(res, new TypeToken<BaseBean<List<AreaBean>>>() {
        }.getType());
        session.setAttribute("area_id", model.getData().get(0).getId());
        //发送Post请求
        return res;
    }


    /**
     * 所有消息事件
     */
    @RequestMapping(path = "/callBackData", method = RequestMethod.POST)
    @ResponseBody
    public void callbackData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug(request.getCookies().toString());

    }

    /**
     * 获取微信二维码
     */
    @RequestMapping(path = "/scan", method = RequestMethod.POST)
    @ResponseBody
    public String scan(@RequestParam Boolean isFirst, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> params = new HashMap<>();
        //此处必须是您连接的外网接口 用于接收二维码图片的接口方法
        params.put("callback_url", "http://www.baidu.com");
        if (isFirst) {
            params.put("service_area", session.getAttribute("area_id"));
        } else
            params.put("account", "licx758");
        params.put("extend", "");
        //发送Post请求
        return HttpUtils.doPost(ApiUrl.scanQRCode, params);
    }


}