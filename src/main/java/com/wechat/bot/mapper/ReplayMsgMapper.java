package com.wechat.bot.mapper;

import com.wechat.bot.entity.KeywordBean;
import com.wechat.bot.entity.ReplayMsg;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplayMsgMapper {

    //根据关键字 微信号 查询设定的回复列表
    List<ReplayMsg> querykeyword(KeywordBean mKey);
}