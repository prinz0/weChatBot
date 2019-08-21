package com.wechat.bot.http;

import com.wechat.bot.util.Constant;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @Author: Mr.li
 * @Desc： http请求方法
 * @Time: 2019-08-16
 */
public class HttpUtils {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    /**
     * get请求
     */
    public static String doGet(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                // 读取服务器返回过来的json字符串数据
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     */
    public static String doPost(String url, Map<String, Object> params) {
        BufferedReader in;
        try {
            // 定义HttpClient
            HttpClient client = HttpClientBuilder.create().build();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            request.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            request.setHeader("apikey", Constant.apikey);
            request.setHeader("hswebtime", Constant.hswebtime);
            request.setHeader("token", Constant.token);
            // 设置参数
            List<NameValuePair> nvps = new ArrayList<>();
            for (Entry<String, Object> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue() + ""));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
            HttpResponse response = client.execute(request);
            logger.error("请求地址：" + url);
            logger.warn("请求参数：" + nvps.toString());
            //获取所有的请求头信息
            Header[] allHeaders = request.getAllHeaders();
            for (Header allHeader : allHeaders) {
                logger.error("请求头：" + allHeader.toString());
            }
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) { // 请求成功
                in = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                String line;
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line).append(NL);
                }
                in.close();
                logger.warn("返回结果：" + sb.toString());
                return sb.toString();
            } else {
                logger.warn("状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     */
    public static String doPost(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.OK.value()) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                System.out.println("请求返回:" + state + "(" + url + ")");
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
