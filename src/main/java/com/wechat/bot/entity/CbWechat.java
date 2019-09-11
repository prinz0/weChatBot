package com.wechat.bot.entity;

public class CbWechat {
    private Integer id;

    private String wechatAccount;

    private Integer uid;

    private Integer status;

    private Integer type;

    private String createTime;

    private String lastTime;

    private String updateTime;

    private String closeonTime;

    private Integer selfmotionadd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount == null ? null : wechatAccount.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime == null ? null : lastTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCloseonTime() {
        return closeonTime;
    }

    public void setCloseonTime(String closeonTime) {
        this.closeonTime = closeonTime == null ? null : closeonTime.trim();
    }

    public Integer getSelfmotionadd() {
        return selfmotionadd;
    }

    public void setSelfmotionadd(Integer selfmotionadd) {
        this.selfmotionadd = selfmotionadd;
    }
}