package com.yuexun.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @apiNote wxController
 * @author zzd
 * @date 2023-09-22 14:02  
 */
@RestController
@Slf4j
public class WxController {

    @Value("${oauth.wx.appid}")
    private String appId;

    @Value("${oauth.wx.appsecret}")
    private String appSecret;

    @Value("${oauth.callback.http}")
    private String callbackUrl;

    /**
     * @apiNote 微信授权登录
     * @param response
     * @return {@link String }
     */
    @GetMapping("/wxLogin")
    private String wxLogin(HttpServletResponse response) throws IOException {
        String url = null;
        try {
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                    + "appid=" + appId
                    + "&redirect_uri=" + URLEncoder.encode(callbackUrl, "UTF-8")
                    + "&response_type=code"
                    + "&scope=snsapi_userinfo"
                    + "&state=" + "yuexun" // 自定义参数
                    + "#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("-------->url-------->{}", url);
        response.sendRedirect(url);
        return url;
    }

    @GetMapping("/wxCallback")
    public String wxCallback(String code, HttpServletResponse response) {
        // 通过code获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId
                + "&secret=" + appSecret
                + "&code=" + code
                + "&grant_type=authorization_code";
        String tokenURL = HttpUtil.get(url);
        JSONObject json = JSONObject.parseObject(tokenURL);
        String openId = json.getString("openid");
        String assessToken = json.getString("access_token");
        // 拉取用户信息
        // 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + assessToken
                + "&openid=" + openId
                + "&lang=zh_CN";
        String info = HttpUtil.get(infoUrl);
        JSONObject userInfo = JSONObject.parseObject(info);
        System.out.println("JSON-----" + userInfo.toString());
        System.out.println("名字-----" + userInfo.getString("nickname"));
        System.out.println("头像-----" + userInfo.getString("headimgurl"));
        try {
            response.sendRedirect("https://www.csdn.net/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "openId:"+openId;
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}

