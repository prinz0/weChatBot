package com.wechat.bot.util;

import org.apache.logging.log4j.LogManager;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-22
 */
public class Utils {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    public static void log(String tag,HttpServletRequest request) {
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String paramName = (String) e.nextElement();
            String value2 = request.getParameter(paramName);
            logger.warn(tag+"--------->"+IPUtil.getRealIp(request)+"---->"+paramName + "=" + value2);

        }
    }

}

