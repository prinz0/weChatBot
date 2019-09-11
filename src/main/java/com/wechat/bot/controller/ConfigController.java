package com.wechat.bot.controller;

import com.wechat.bot.http.ApiUrl;
import com.wechat.bot.http.HttpUtils;
import com.wechat.bot.util.Constant;
import com.wechat.bot.util.Utils;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mr.li
 * @Desc： 回调配置模块
 * @Time: 2019-08-16
 */
@Controller
@RequestMapping("/config")
public class ConfigController {


    /**
     * 扫码消息回调事件处理（被微控服务器请求的开放接口）
     */
    @RequestMapping(path = "/callBackData", method = RequestMethod.POST)
    @ResponseBody
    public void callbackData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log("扫码回调", request);
    }

    /*
     * 配置全局回调地址
     * */
    @RequestMapping(path = "/setUrl", method = RequestMethod.POST)
    @ResponseBody
    public String setUrl(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<>();
        params.put("callbackSend", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/callbackSend");
        params.put("delfriendlog", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/delfriendLog");
        params.put("newfriendlog", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/newfriendLog");
        params.put("messagelog", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/messageLog");
        params.put("crowdlog", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/crowdLog");
        params.put("addfriendlog", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/addfriendLog");
        params.put("wacatout", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/wacatOut");
        params.put("addgrouplog", ApiUrl.serviceUrl + Constant.CONT_CONFIG + "/addgroupLog");
        return HttpUtils.doPost(ApiUrl.callBackUrl, params);
    }
}