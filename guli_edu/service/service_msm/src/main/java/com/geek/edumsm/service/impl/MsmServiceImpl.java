package com.geek.edumsm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.edumsm.service.MsmService;
import com.geek.edumsm.utils.RandomUtil;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MsmServiceImpl
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/28 20:53
 * @Version 1.0
 **/
@Service
public class MsmServiceImpl implements MsmService {


    @Override
    public boolean send(String phone,String code) {

        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        try {
            DefaultProfile profile =
                    DefaultProfile.getProfile("default", "LTAI5tPvpheNZzHZqu2Htgko", "T18RmuOTonkTn7oyzGoUCrgIqWx28v");
            IAcsClient client = new DefaultAcsClient(profile);

            //设置相关固定参数
            CommonRequest request = new CommonRequest();
            //request.setProtocol(ProtocolType.HTTPS);
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");

            //设置发送的相关参数
            request.putQueryParameter("PhoneNumbers", phone);//手机号
            request.putQueryParameter("SignName", "深圳技师学院茶艺社");//阿里云申请的签名名称
            request.putQueryParameter("TemplateCode", "SMS_204127522");//阿里云申请的模板Code
            request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码数据，需要用json数据传递

            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            //是否发送成功
            if (!response.getHttpResponse().isSuccess()) {
                return false;
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }
}
