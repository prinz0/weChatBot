package com.wechat.bot.entity;

public
/**
 * @Author: Mr.li
 * @Desc： 群聊回调
 * @Time: 2019-08-23
 */
class GroupChat extends BaseChat{
    private String account;
    private String name;

    public String getAccount() {
        return account == null ? "" : account;
    }

    public void setAccount(String account) {
        this.account = account == null ? "" : account;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }
}
