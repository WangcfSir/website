package com.website.system.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 类描述：发送验证码工具类
 */

public class QueryUtil {
    public static String queryArguments(String ACCOUNT_SID, String AUTH_TOKEN, String smsContent, String to) {

        String timestamp = getTimestamp(); //时间戳
        String sig = MD5(ACCOUNT_SID, AUTH_TOKEN, timestamp);//签名认证

        String str = "accountSid=" + ACCOUNT_SID + "&smsContent=" +
                smsContent + "&to=" + to + "&timestamp=" + timestamp + "&sig=" + sig;
        return str;
    }

    /**
     * MD5加密
     *
     * @param args
     * @return
     */
    public static String MD5(String... args) { //动态参数
        StringBuffer result = new StringBuffer();
        if (args == null || args.length == 0) {
            return "";
        } else {
            StringBuffer str = new StringBuffer();
            for (String string : args) {
                str.append(string);
            }
            System.out.println("加密前：\t" + str.toString());

            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(str.toString().getBytes());
                for (byte b : bytes) {
                    String hex = Integer.toHexString(b & 0xff);  //转化十六进制
                    if (hex.length() == 1) {
                        result.append("0" + hex);
                    } else {
                        result.append(hex);
                    }
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        System.out.println("加密后：\t" + result.toString());
        return result.toString();
    }

    /*
     * 获取时间戳
     */
    public static String getTimestamp() {
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime());
        return timestamp;

    }
}