package com.wechat.bot.service;

import com.wechat.bot.entity.KeywordBean;
import com.wechat.bot.entity.ReplayMsg;
import com.wechat.bot.mapper.ReplayMsgMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-24
 */

@Service
public class ReplayActionService {

    @Autowired
    private ReplayMsgMapper mReplayMsgMapper;

    public List<ReplayMsg> querykeyword(KeywordBean mKey) {
        return mReplayMsgMapper.querykeyword(mKey);
    }


}
