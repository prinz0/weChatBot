package com.wechat.bot.entity;

import com.wechat.bot.util.DateUtils;

import java.text.ParseException;

public class CbGroup {

    private Integer id;

    private String beloneAccount;

    private String number;

    private String name;

    private Integer disturb;

    private String thumb;

    private String content;
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeloneAccount() {
        return "licx758";
    }

    public void setBeloneAccount(String beloneAccount) {
        this.beloneAccount = beloneAccount == null ? null : beloneAccount.trim();
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public String getNumber() {
        return number == null ? "" : number;
    }

    public void setNumber(String number) {
        this.number = number == null ? "" : number;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public Integer getDisturb() {
        return disturb;
    }

    public void setDisturb(Integer disturb) {
        this.disturb = disturb;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getCreatetime() throws ParseException {
        return String.valueOf(DateUtils.getCurDateLong() / 1000);
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}