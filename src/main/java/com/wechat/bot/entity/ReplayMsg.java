package com.wechat.bot.entity;

public class ReplayMsg {
    private Integer id;

    private Integer actionId;

    private Integer groupid;

    private Integer createTime;

    private String replayMsg;

    private Integer type;

    private String groupAccount;

    public String getGroupAccount() {
        return groupAccount == null ? "" : groupAccount;
    }

    public void setGroupAccount(String groupAccount) {
        this.groupAccount = groupAccount == null ? "" : groupAccount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getReplayMsg() {
        return replayMsg;
    }

    public void setReplayMsg(String replayMsg) {
        this.replayMsg = replayMsg == null ? null : replayMsg.trim();
    }
}