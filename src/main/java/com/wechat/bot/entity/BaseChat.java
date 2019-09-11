package com.wechat.bot.entity;

public
/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-23
 */
class BaseChat {

    private String my_account;
    private String my_name;
    private String my_account_alias;
    private String to_account;
    private String to_account_alias;
    private String to_name;
    private String g_number;
    private String g_name;
    private String content;
    private int content_type;
    private String file_name;
    private String voice_len;

    public String getMy_account() {
        return my_account;
    }

    public void setMy_account(String my_account) {
        this.my_account = my_account;
    }

    public String getMy_name() {
        return my_name;
    }

    public void setMy_name(String my_name) {
        this.my_name = my_name;
    }

    public String getMy_account_alias() {
        return my_account_alias;
    }

    public void setMy_account_alias(String my_account_alias) {
        this.my_account_alias = my_account_alias;
    }

    public String getTo_account() {
        return to_account;
    }

    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }

    public String getTo_account_alias() {
        return to_account_alias;
    }

    public void setTo_account_alias(String to_account_alias) {
        this.to_account_alias = to_account_alias;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getG_number() {
        return g_number;
    }

    public void setG_number(String g_number) {
        this.g_number = g_number;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContent_type() {
        return content_type;
    }

    public void setContent_type(int content_type) {
        this.content_type = content_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getVoice_len() {
        return voice_len;
    }

    public void setVoice_len(String voice_len) {
        this.voice_len = voice_len;
    }
}
