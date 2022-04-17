package com.geek.edumsm.controller;

import com.geek.commonutils.R;
import com.geek.commonutils.RedisUtils;
import com.geek.edumsm.service.MsmService;
import com.geek.edumsm.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MsmController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/28 20:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/edumsm/msm")
@Slf4j
@Api(tags = "阿里云短信服务")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation("发送手机验证码")
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable("phone") String phone) {
        if (StringUtils.isEmpty(phone)) {
            return R.error().message("手机号码为空");
        }
        //生成6位随机值，传递给短信服务发送
        String code = RandomUtil.getSixBitRandom();
        //调用service发送短信的方法
        boolean isSend = msmService.send(phone, code);
        if (isSend) {
            //设置验证码有效时间5分钟
            redisUtils.set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送出错");
        }
    }

}
