package com.wechat.bot.mapper;

import com.wechat.bot.entity.CbGroup;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CbGroupMapper {


    // 批量插入
    int insertGroupList(@Param("list") List<CbGroup> list);

    //查询是否是管理群
    List<CbGroup> queryIsManage(Map<String, Object> map);
}