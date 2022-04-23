package com.geek.eduorder.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.commonutils.result.R;
import com.geek.eduorder.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "微信Native支付接口")
@RequestMapping("/eduorder/pay-log")
public class PayController {

    @Autowired
    private PayService payService;

    @ApiOperation("生成微信支付二维码")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable("orderNo") String orderNo) throws Exception {
        //返回信息，包含二维码地址及其他信息
        Map<String,Object> map = payService.createNative(orderNo);
        log.info("返回二维码的map："+map);
        return R.ok().data(map);
    }

    @ApiOperation("查询支付状态")
    @GetMapping("/getPayStatus/{orderNo}")
    public R getPayStatus(@PathVariable("orderNo") String orderNo) throws Exception {
        Map<String,String> map = payService.getPayStatus(orderNo);//返回一个包含订单状态的map集合
        if (map.isEmpty()){
            return R.error().message("支付出错");
        }
        //判断交易状态trade_state是否成功
        if (StringUtils.equals(map.get("trade_state"),"SUCCESS")){
            log.info("用户支付成功！");
            //成功：更改订单状态 添加支付日志
            payService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.error().code(25000).message("支付中");
    }
}

