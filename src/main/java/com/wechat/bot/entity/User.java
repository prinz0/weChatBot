package com.wechat.bot.entity;

/**
 * @Author: Mr.li
 * @Desc： 用户实体类
 * @Time: 2019-08-16
 */
public class User {
    private String phone;
    private String pass;

    public User(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
    }

    public String getPass() {
        return pass == null ? "" : pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? "" : pass;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? "" : phone;
    }
}
