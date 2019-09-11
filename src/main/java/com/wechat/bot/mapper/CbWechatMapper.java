package com.wechat.bot.mapper;

import com.wechat.bot.entity.CbWechat;

import org.springframework.stereotype.Repository;

@Repository
public interface CbWechatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CbWechat record);

    int insertSelective(CbWechat record);

    CbWechat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CbWechat record);

    int updateByPrimaryKey(CbWechat record);

    //根据微信号 查询微信数据
    CbWechat selectWechat(String account);
}