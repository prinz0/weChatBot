package com.wechat.bot.entity;

public enum ResponseCode {

    SUCCESS(10,"操作成功"),

    ERROR(11,"操作失败"),

    NOT_ROLE(13,"无角色"),

    NOT_LOGIN(12,"未登录");

    private int code;
    private String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

}

