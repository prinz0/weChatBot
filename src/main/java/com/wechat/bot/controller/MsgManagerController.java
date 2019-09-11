package com.wechat.bot.controller;


import com.wechat.bot.entity.BobMsg;
import com.wechat.bot.entity.CbGroup;
import com.wechat.bot.entity.CbWechat;
import com.wechat.bot.entity.GroupChat;
import com.wechat.bot.entity.KeywordBean;
import com.wechat.bot.entity.ReplayMsg;
import com.wechat.bot.service.CbGroupService;
import com.wechat.bot.service.CbWechatService;
import com.wechat.bot.service.ReplayActionService;
import com.wechat.bot.util.Constant;
import com.wechat.bot.util.GsonUtil;
import com.wechat.bot.util.MessageUtil;
import com.wechat.bot.util.Utils;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mr.li
 * @Desc： 消息回调处理
 * @Time: 2019-08-22
 */
@Controller
@RequestMapping(Constant.CONT_CONFIG)
public class MsgManagerController {

    @Autowired
    private ReplayActionService mKeywordActionService;

    @Autowired
    private CbGroupService mCbGroupService;

    @Autowired
    private CbWechatService mCbWechatService;


    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();




    //删除好友
    @RequestMapping(path = "/delfriendLog", method = RequestMethod.POST)
    @ResponseBody
    public void delfriendLog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_DELFRIEND, request);
    }

    //好友添加通知（待同意）
    @RequestMapping(path = "/newfriendLog", method = RequestMethod.POST)
    @ResponseBody
    public void newfriendLog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_NEWFRIENDLOG, request);
        //根据微信号 查询是否开启 自动同意好友
        String myAccount = request.getParameter("my_account");
        String toAccount = request.getParameter("account");
        CbWechat mWechat = mCbWechatService.selectWechat(myAccount);
        if (mWechat.getSelfmotionadd() == 0) {//如果开启自动添加好友
            if (MessageUtil.passAddFriend(myAccount, toAccount) == 1) {//添加好友成功
                logger.info("自动添加好友------>免验证添加好友成功");
                GroupChat m = new GroupChat();
                m.setMy_account(myAccount);
                m.setTo_account(toAccount);
                sendMsgOfKey(m, false, "[#pass]");//发送模板消息
            } else
                logger.info("自动添加好友------>免验证添加好友失败");
        }
    }

    //单聊信息
    @RequestMapping(path = "/messageLog", method = RequestMethod.POST)
    @ResponseBody
    public void messageLog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_MESSAGELOG, request);
        //关键字自动回复
        sendMsgOfKey(request, false);
    }

    //群聊消息
    @RequestMapping(path = "/crowdLog", method = RequestMethod.POST)
    @ResponseBody
    public void crowdLog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_CROWDLOG, request);
        GroupChat msg = GsonUtil.GsonToBean(request.getParameter("data"), GroupChat.class);
        //查询是否是管理群
        Map<String, Object> map = new HashMap<>();
        map.put("account", msg.getMy_account());
        map.put("manage", 1);
        map.put("groupid", msg.getG_number());
        List<CbGroup> cbGroups = mCbGroupService.queryIsManage(map);
        if (cbGroups != null && cbGroups.size() > 0) {
            sendMsgOfKey(request, true);
        }

    }

    //好友添加通知（已同意）
    @RequestMapping(path = "/addfriendLog", method = RequestMethod.POST)
    @ResponseBody
    public void addfriendLog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_ADDFRIENDLOG, request);

    }

    // 通用回调地址：包含以下回调
    //1.加好友通知
    //2.接收查询的微信信息
    //3.获取微信所有标签列表
    @RequestMapping(path = "/callbackSend", method = RequestMethod.POST)
    @ResponseBody
    public void callbackSend(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_CALLBACKSEND, request);

    }

    //上下线通知
    @RequestMapping(path = "/wacatOut", method = RequestMethod.POST)
    @ResponseBody
    public void wacatOut(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_WACATOUT, request);
        BobMsg bobMsg = GsonUtil.GsonToBean(request.getParameter("data"), BobMsg.class);
        //上线时同步好友列表到库中
        if (bobMsg.getType() == 1)
            mCbGroupService.insertGroupList(MessageUtil.syncFriendList());

    }


    //入群消息
    @RequestMapping(path = "/addgroupLog", method = RequestMethod.POST)
    @ResponseBody
    public void addgroupLog(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Utils.log(Constant.CALLBACK_ADDGROUPLOG, request);
        GroupChat msg = GsonUtil.GsonToBean(request.getParameter("data"), GroupChat.class);
        //查询是否是管理群
        Map<String, Object> map = new HashMap<>();
        map.put("account", msg.getMy_account());
        map.put("manage", 1);
        map.put("groupid", msg.getG_number());
        List<CbGroup> cbGroups = mCbGroupService.queryIsManage(map);
        if (cbGroups != null && cbGroups.size() > 0) {
            msg.setContent(cbGroups.get(0).getContent());
            //是管理群 新成员入群开启群内@
            MessageUtil.groupAt(msg);
        }
    }


    /**
     * 关键字消息处理
     *
     * @param request
     * @param isgroup
     */
    private void sendMsgOfKey(HttpServletRequest request, boolean isgroup) {
        this.sendMsgOfKey(request, isgroup, null);
    }

    /**
     * 消息处理
     *
     * @param request 请求参数
     * @param isgroup 是否群聊
     * @param content 是否是特殊含义
     */
    private void sendMsgOfKey(Object request, boolean isgroup, String content) {
        GroupChat mGroupChat = null;
        if (request instanceof HttpServletRequest) {
            mGroupChat = GsonUtil.GsonToBean(((HttpServletRequest) request).getParameter("data"), GroupChat.class);
        } else if (request instanceof GroupChat) {
            mGroupChat = (GroupChat) request;
        }
        //查询回复列表
        List<ReplayMsg> mList = mKeywordActionService.querykeyword(new KeywordBean(isgroup ? 1 : 0, content != null ? content : mGroupChat.getContent(), mGroupChat.getMy_account()));
        for (ReplayMsg keywordActionBean : mList) {
            switch (keywordActionBean.getActionId()) {
                case 0://发送文本消息
                    MessageUtil.sendMsg(mGroupChat, keywordActionBean.getReplayMsg(), isgroup);
                    break;
                case 2://发送群邀请
                    MessageUtil.sendAddGroup(mGroupChat, keywordActionBean.getGroupAccount());
                    break;
                case 4://发送名片
                    MessageUtil.sendWacatCard(mGroupChat, isgroup, isgroup ? 2 : 1,"LoChaX");
                    break;
            }

        }

    }

}
