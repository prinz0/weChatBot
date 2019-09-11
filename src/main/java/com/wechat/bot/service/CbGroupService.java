package com.wechat.bot.service;

import com.wechat.bot.entity.CbGroup;
import com.wechat.bot.entity.KeywordBean;
import com.wechat.bot.entity.ReplayMsg;
import com.wechat.bot.mapper.CbGroupMapper;
import com.wechat.bot.mapper.ReplayMsgMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-24
 */

@Service
public class CbGroupService {

    @Autowired
    private CbGroupMapper mCbGroupMapper;

    public int insertGroupList(List<CbGroup> list) {
        return mCbGroupMapper.insertGroupList(list);
    }

    public List<CbGroup> queryIsManage(Map<String, Object> map) {
        return mCbGroupMapper.queryIsManage(map);
    }

}
