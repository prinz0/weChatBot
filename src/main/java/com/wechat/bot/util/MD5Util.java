package com.wechat.bot.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Mr.li
 * @Desc：
 * @Time: 2019-08-16
 */
public class MD5Util {

    public static String MD5(String str) throws Exception {
        String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        // 消息签名（摘要）
        MessageDigest md5 = null;
        try {
            // 参数代表的是算法名称
            md5 = MessageDigest.getInstance("md5");
            byte[] result = md5.digest(str.getBytes());

            StringBuilder sb = new StringBuilder(32);
            // 将结果转为16进制字符  0~9 A~F
            for (int i = 0; i < result.length; i++) {
                // 一个字节对应两个字符
                byte x = result[i];
                // 取得高位
                int h = 0x0f & (x >>> 4);
                // 取得低位
                int l = 0x0f & x;
                sb.append(chars[h]).append(chars[l]);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


    public static String encode(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static String encode(String password, Object mix) {
        if (mix != null)
            return DigestUtils.md5Hex(mix.toString() + password);
        return DigestUtils.md5Hex(password);
    }

}
