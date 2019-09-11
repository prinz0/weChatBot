package com.wechat.bot.util;

import com.google.gson.reflect.TypeToken;
import com.wechat.bot.entity.BaseBean;
import com.wechat.bot.entity.CbGroup;
import com.wechat.bot.entity.GroupChat;
import com.wechat.bot.http.ApiUrl;
import com.wechat.bot.http.HttpUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Mr.li
 * @Desc： 消息处理类
 * @Time: 2019-08-24
 */
public class MessageUtil {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();


    //发送消息
    private static void sendlMessage(GroupChat mchat, Object content, String contenttype, Boolean isgroup, String imgMd5, String imgSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("my_account", mchat.getMy_account());
        if (isgroup)
            params.put("to_account", mchat.getG_number());
        else
            params.put("to_account", mchat.getTo_account());
        if (content instanceof File)
            params.put("content", (File) content);
        else
            params.put("content", content + "");
        params.put("content_type", contenttype);
        params.put("type", isgroup ? "2" : "1");
        if (imgSize != null)
            params.put("img_size", imgSize);
        if (imgMd5 != null)
            params.put("img_md5", imgMd5);
        String strResult = HttpUtils.doPost(ApiUrl.sendMsg, params);
        BaseBean baseModel = GsonUtil.GsonToBean(strResult, BaseBean.class);
        logger.debug("发送消息" + baseModel.getMsg());
    }

    private static int page = 0;
    private static List<CbGroup> allGroup = new ArrayList<>();

    //同步群列表
    public static List<CbGroup> syncFriendList() {
        if (page == 0)
            allGroup.clear();
        Map<String, Object> params = new HashMap<>();
        params.put("account", "licx758");
        params.put("page", page);
        params.put("num", 10);
        //新增或修改群列表
        String res = HttpUtils.doPost(ApiUrl.getGroupList, params);
        BaseBean<List<CbGroup>> model = GsonUtil.GsonToBean(res, new TypeToken<BaseBean<List<CbGroup>>>() {
        }.getType());
        List<CbGroup> data = model.getData();
        if (data != null && data.size() > 0) {
            allGroup.addAll(data);
            page++;
            syncFriendList();
        } else
            page = 0;

        return allGroup;
    }

    /**
     * 通过好友添加申请
     *
     * @param myaccount 使用微信号
     * @param toaccount 对方微信号
     * @return 标志
     */
    public static int passAddFriend(String myaccount, String toaccount) {
        Map<String, Object> params = new HashMap<>();
        params.put("my_account", myaccount);
        params.put("account", toaccount);
        //新增或修改群列表
        String res = HttpUtils.doPost(ApiUrl.passAddFriends, params);
        BaseBean gsonToBean = GsonUtil.GsonToBean(res, BaseBean.class);
        return gsonToBean.getCode();
    }

    /**
     * 发送名片信息
     *
     * @param mGroupChat 信息封装
     * @param isgroup    群号：微信号
     * @param type       1单聊 2群聊
     */
    public static int sendWacatCard(GroupChat mGroupChat, boolean isgroup, int type,String toaccount) {
        Map<String, Object> params = new HashMap<>();
        params.put("my_account", mGroupChat.getMy_account_alias() != null ? mGroupChat.getMy_account_alias() : mGroupChat.getMy_account());
        params.put("to_account", isgroup ? mGroupChat.getG_number() : mGroupChat.getTo_account());
        params.put("card_name", toaccount);
        params.put("type", type);
        String res = HttpUtils.doPost(ApiUrl.wacatCard, params);
        BaseBean gsonToBean = GsonUtil.GsonToBean(res, BaseBean.class);
        return gsonToBean.getCode();
    }

    //发送文本消息
    public static void sendMsg(GroupChat mchat, String content, boolean isgroup) {
        sendlMessage(mchat, content, "1", isgroup, null, null);
    }

    //发送群链接邀请
    public static int sendAddGroup(GroupChat mchat, String g_number) {
        Map<String, Object> params = new HashMap<>();
        params.put("g_number", g_number);
        params.put("my_account", mchat.getMy_account());
        params.put("account", StringUtils.isEmpty(mchat.getTo_account_alias()) ? mchat.getTo_account() : mchat.getTo_account_alias());
        String res = HttpUtils.doPost(ApiUrl.invateMember, params);
        BaseBean gsonToBean = GsonUtil.GsonToBean(res, BaseBean.class);
        return gsonToBean.getCode();
    }

    //群聊@成员
    public static int groupAt(GroupChat mchat) {
        Map<String, Object> params = new HashMap<>();
        params.put("my_account", mchat.getMy_account_alias() != null ? mchat.getMy_account_alias() : mchat.getMy_account());
        params.put("account",mchat.getG_number());
        params.put("atUser", mchat.getAccount());
        params.put("content", "欢迎@"+ mchat.getName()+" "+mchat.getContent());
        String res = HttpUtils.doPost(ApiUrl.groupAt, params);
        BaseBean gsonToBean = GsonUtil.GsonToBean(res, BaseBean.class);
        return gsonToBean.getCode();
    }

}
