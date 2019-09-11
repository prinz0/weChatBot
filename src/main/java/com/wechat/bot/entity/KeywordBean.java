package com.wechat.bot.entity;

/**
 * @Author: Mr.li
 * @Desc： 关键字请求类
 * @Time: 2019-08-27
 */
public class KeywordBean {


    private int type;
    private String contentKey;
    private String account;

    public KeywordBean(int type, String contentKey, String account) {
        this.type = type;
        this.contentKey = contentKey;
        this.account = account;
    }

    public KeywordBean(String contentKey, String account) {
        this.contentKey = contentKey;
        this.account = account;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContentKey() {
        return contentKey == null ? "" : contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey == null ? "" : contentKey;
    }

    public String getAccount() {
        return account == null ? "" : account;
    }

    public void setAccount(String account) {
        this.account = account == null ? "" : account;
    }
}
