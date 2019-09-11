package com.wechat.bot.entity;

public
/**
 * @Author: Mr.li
 * @Desc： 上下线回调
 * @Time: 2019-08-23
 */
class BobMsg extends BaseChat{
    private int type;

    public BobMsg(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
