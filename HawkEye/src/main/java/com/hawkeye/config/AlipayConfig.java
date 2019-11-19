package com.hawkeye.config;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 按照我文章图上的信息填写
    public static String app_id = "2016101400682567";

    // 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxH47zT1LCoF5XWEljt4rVRCPLZJCKCBJToA7QTgsfSmzFrz9T8vZLJX9Yj3+MvaUkCOwFVNzIVxWsy5ZMKrQNv3n9EbLfQkf8wGMNKzHo9GwInqIaZnJfJBKy41tlX38kFwwZO0hZV9cDGoOsaDhIfRryYu2W0ZSWJ4W0AwB1pAE73Q9ABis9WYizSU/1mMlmXYxGl3mOum0FE6R6StJ2GP6A6hhbmzkErMrsQF2oOJQL2DDzerC4iQ2ftU32DPdLARk/QBo3HgdKPr97QfZp0aYmnlxdtlYtcYDhorp6qXL/pomwHLrUl0Y/H1PtTjM2ogbBTJACLJBOsqymngrLAgMBAAECggEBAIEJrboigveZn8FgZ28HK1S9pJor9IUq5jcYN1KYWM/+B4ppZuiA69/JPKJHQLRHAUVkXpZvwb0OfJL7as6oma4eXf/5Yef2wL0pZ8ATRPQjBYWuFAjJs+8ON7Utn8sVrGmdhMno/4dfUBxJt73l24Mr6XLGkbK7ktVli6meOjp4/g2VB9JR4Sk3YXzjg1Ipo03Hc6nNXU9B8+a++RUrLi2+AKbXRN80rCYmjJ1pEPcDB40u1mzwRceg8dNACr6/Lx+AnZVkenvOa/gPDFS8jVWzTqN56DrDUB/aBXptTEfkw8E5Dk+lomJ5VTVg0BJ2d3jq/kcPdYsfzbH+sdGjpJECgYEA7MQX1p1yOTOtrXNqDQywcVYNjmESgNeeA0XVCKjv++GrcNJc9kybLrb1IJmEiKbsQ5pCdJAyqfwFtszU/4v+iiny8mOEiHfoEq4b5oTf5cu5O1pF074yD4lt/bp6xjyj85KsZb4R/RRpu+ESdg9+4pgq6vnEXwiMkZNK0qS0PrMCgYEAv4Ma1inU1OPysWjn9mSdnSSlUp9lvQTRg1MUzxC2yqDzwfR9xIInKzygJk9hAZaSNpZ+Wufi1u+xbUnoxcvZ1pkHAQfZci+L9dRvZAcXU36BZlaea9OZNuXCceQPOhhSgY3tLvMTu5mbQtZAWpH0CzEu/bMHohRbbdiZQwGUD4kCgYEAidI34ijyZZPMhIwLbp81UjP6XifpgnCAvXz33jugvqmFh34cuQ6swoujsx9KA9XO8CcqifmqVEKs/NTNJBhUyg5TKn99lYbe44jQGua8AmMNZxxMS06Ki3q/ORRFBYIlGdMGYY87n2szhTcl7O1mOfbD/NX5Vix+zvrDckyH03sCgYB9Y3yFrhvQS3LSH2qIkzXy9lRb9UsqZ+Y5QoNEU6AqqRkP62EQ8Vpt36LHkn9kB86xePU/1cVajtMvSA3ku2RkMgs47Ie9UE4l5TINXusx2okklR/iX0dYfzRBV1103wneEyuw6qwHzzmtmBPIePk8/rXSm44q24NFN2SZT4Y+aQKBgDv8H/xwT98TdVgoohFjk3Kmio4iU/gMdBwHIN6s+M3VWjITshHOD+syYkZrO4GAGNCCxwnmPTDduRqY2oVya2HU70yRJCS2Y4Y6LSs1J7PGBIJ0bLAHuOECWBgJyN2vFfKWMu713sa55CL08H6HRLy7LY+6/8AXH/LEhrFxfuFT";

    // 支付宝公钥,对应APPID下的支付宝公钥。 按照我文章图上的信息填写支付宝公钥，别填成商户公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt5p5gsrOXIFAknU2BrPm/7nCZHUaBamObAjKHcAOm1OBYU2hR8lOpHb58Yy281Pk5UjGvsbGSeXaptE9oO/2kZKZpgzaOTr9OS3+mV8/nLFZ1mSKwX0atROk55kSIceapBrLM1EZY8haKqKiZv7llqkZ+TNWzkwLatkL7RQ6X+gqRMQeMQjCTEFnaBLAhjXt5G0/fzPYV0d6jplYnwrUz2YnGWAfCdp91TNtdDMRwWO0mO18cA7s9P+Pmh6SNZMjOT6NokPWvwAmLD/07Lm71e4vaK3hb16JWDxiey/+6qCswy72ZsJMQpi/oVF12egpvEIa80+eiaJbvWTeE3b+CQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url = "http://www.baidu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
