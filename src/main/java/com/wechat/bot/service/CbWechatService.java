package com.wechat.bot.service;

import com.wechat.bot.entity.CbGroup;
import com.wechat.bot.entity.CbWechat;
import com.wechat.bot.mapper.CbGroupMapper;
import com.wechat.bot.mapper.CbWechatMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-24
 */

@Service
public class CbWechatService {

    @Autowired
    private CbWechatMapper mCbWechatMapper;

    public CbWechat selectWechat(String account) {
        return mCbWechatMapper.selectWechat(account);
    }


}
