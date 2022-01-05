package com.geek.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantPropertiesUtil
 * @Description 读取yml配置的内容
 * @Author Lambert
 * @Date 2022/1/5 14:41
 * @Version 1.0
 **/
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.file.enpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String BUCKET_NAME;

    /**
     * 项目启动时执行该方法
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
