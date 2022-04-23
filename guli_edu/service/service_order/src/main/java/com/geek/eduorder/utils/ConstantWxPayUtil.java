package com.geek.eduorder.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantWxPayUtil
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/21 16:34
 * @Version 1.0
 **/
@Component
public class ConstantWxPayUtil implements InitializingBean {
    @Value("${wx.pay.appid}")
    private String appid;

    @Value("${wx.pay.mchId}")
    private String mchId;

    @Value("${wx.pay.mchKey}")
    private String mchKey;

    @Value("${wx.pay.notifyUrl}")
    private String notifyUrl;

    public static String WX_PAY_APPID;
    public static String WX_PAY_MCH_ID;
    public static String WX_PAY_MCH_KEY;
    public static String WX_PAY_NOTIFY_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_PAY_APPID = appid;
        WX_PAY_MCH_ID = mchId;
        WX_PAY_MCH_KEY = mchKey;
        WX_PAY_NOTIFY_URL = notifyUrl;
    }
}
