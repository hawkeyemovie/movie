package com.hawkeye.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hawkeye.pojo.User;
import com.hawkeye.util.RedisUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * redis操作工具类
     */
    @Autowired
    RedisUtil redisUtil;

    private final int appid = 1400281948;
    private final String appkey = "a7e38de04a654e5b829348168e235386";

    @RequestMapping("/smssingle")
    public String send(String phoneNumbers) {
        int yzm = random();
        String num = Integer.toString(yzm);
        SmsSingleSenderResult result = null;
        try {

            /**
             * 注意这里的内容要于平台模板内容完全一致  否则发送失败
             */
            String smsSign = "【映像电影】" + yzm + "（登录验证码）。工作人员不会向您索要，请勿向任何人泄露，以免造成账户或资金损失。"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.send(0, "86", phoneNumbers, smsSign, "", "");

            JSONObject json = JSONObject.parseObject(result.toString());
            String state = json.getString("result");
            System.out.println("状态码为："+state);
            if ("0".equals(state)) {
                System.out.println("短信发送成功！");
                redisUtil.set(phoneNumbers,num);
//                redisUtil.expire(phoneNumbers, 5,TimeUnit.MINUTES);
                return "true";
            } else {
                System.out.println("短信发送失败！");
                return "false";
            }
        } catch (HTTPException e) {
            System.out.println("HTTP响应码错误");
            return result.toString();
        } catch (JSONException e) {
            System.out.println("json解析错误");
            return result.toString();
        } catch (IOException e) {
            System.out.println(" 网络IO错误");
            return result.toString();
        }
    }



    private static int random() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        int[] c = new int[6];
        for (int i = 0; i < 6; i++) {
            c[i] = r.nextInt(9) + 1;
            sb.append(c[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    @RequestMapping("/islogin")
    public String isLogin(@RequestParam("phoneNumbers")String phoneNumbers){
        try {
            //从redis中读取数据
            JSONObject jsonObject  = JSONObject.parseObject(redisUtil.get(phoneNumbers));
            User user = JSONObject.toJavaObject(jsonObject, User.class);//得到用户对象
            System.out.println("==============================================>已登录");
            return "true";
        } catch (Exception e) {
            System.out.println("==============================================>未登录");
            return "false";
        }
    }

    @RequestMapping("/getuser")
    public String getuser(@RequestParam("phoneNumbers")String phoneNumbers){
       return redisUtil.get(phoneNumbers);
    }
}
