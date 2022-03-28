package com.geek.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantVodPropertiesUtils
 * @Description 读取yml配置的内容
 * @Author Lambert
 * @Date 2022/3/19 1:14
 * @Version 1.0
 **/
@Component
public class ConstantVodPropertiesUtil implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;


    /**
     * 项目启动时执行该方法
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
