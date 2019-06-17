package com.website.system.utils;


import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class EmailUtil {
    public static void main(String[] args) {
        //Email("");
        //SendEmail("@qq.com");
    }
    public static Object Email(String email){

        String host = "https://emailcert.market.alicloudapi.com";
        String path = "/email";
        String method = "GET";
        String appcode = "75c5d7d1703544c794ec1f2f1729330a";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("email", email);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            //System.out.println("第一个："+EntityUtils.toString(response.getEntity()));
            JSONObject jsonObj= JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            String value= jsonObj.getString("status");
            System.out.print("value:"+value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "000";//异常
        }

    }
    public static Object SendEmail(String email){
        String host = "http://taole.market.alicloudapi.com";
        String path = "/taole-mail-service/service/rest/mail.SendMail/collection/send";
        String method = "POST";
        String appcode = "75c5d7d1703544c794ec1f2f1729330a";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        String bodys = "{" +
                "\"from\":\"\","+
                "\"to\":\""+email+"\","+
                "\"subject\":\"协会注册通知\","+
                "\"content\":\"您的邮箱:"+email+" 已注册成功\""+
                "}";

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
           // System.out.println(response.toString());
            //获取response的body
           /* System.out.println(EntityUtils.toString(response.getEntity()));*/
            JSONObject jsonObj= JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            String value= jsonObj.getString("code");
            System.out.print("value:"+value);
            return "value";
        } catch (Exception e) {
            e.printStackTrace();
            return "000";//异常
        }

    }
}
