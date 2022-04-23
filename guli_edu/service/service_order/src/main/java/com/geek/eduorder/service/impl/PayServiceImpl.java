package com.geek.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.eduorder.entity.Order;
import com.geek.eduorder.entity.PayLog;
import com.geek.eduorder.mapper.PayLogMapper;
import com.geek.eduorder.service.OrderService;
import com.geek.eduorder.service.PayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.eduorder.utils.ConstantWxPayUtil;
import com.geek.eduorder.utils.HttpClient;
import com.geek.servicebase.exception.GuliException;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@Service
@Slf4j
public class PayServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayService {

    @Autowired
    private OrderService orderService;

    @Override
    public Map<String, Object> createNative(String orderNo) throws Exception {
        log.info("正在生成微信支付二维码...");
        //根据订单号查询订单信息
        Order order = orderService.getOrderByOrderNo(orderNo);
        //1、设置微信支付参数
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", ConstantWxPayUtil.WX_PAY_APPID);//公众账号ID
        paramsMap.put("mch_id", ConstantWxPayUtil.WX_PAY_MCH_ID);//商户号
        paramsMap.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
        paramsMap.put("body", order.getCourseTitle());//商品描述
        paramsMap.put("out_trade_no", orderNo);//商户订单号
        paramsMap.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");//订单金额 单位为分
        paramsMap.put("spbill_create_ip", "127.0.0.1");//终端IP
        paramsMap.put("notify_url", ConstantWxPayUtil.WX_PAY_NOTIFY_URL);
        paramsMap.put("trade_type", "NATIVE");

        //2、发送httpclient请求 传递xml格式参数 微信支付提供固定地址
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
        //client设置参数

        client.setXmlParam(WXPayUtil.generateSignedXml(paramsMap, ConstantWxPayUtil.WX_PAY_MCH_KEY));
        client.setHttps(true);
        client.post();
        String xml = client.getContent();
        log.info("xml:"+xml);
        //3、返回第三方数据
        Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
        if (!StringUtils.equals(resultMap.get("return_code"), "SUCCESS")){
            //调用微信支付返回二维码成功
            throw new GuliException(20001, "调用微信支付出错");
        }
        //4、封装返回结果集
        Map<String, Object> map = new HashMap<>();
        map.put("out_trade_no", orderNo);
        map.put("course_id", order.getCourseId());
        map.put("total_fee", order.getTotalFee());
        map.put("result_code", resultMap.get("result_code"));//状态码
        map.put("code_url", resultMap.get("code_url"));//二维码地址
        //微信支付二维码2小时过期，可采取2小时未支付取消订单
        //redisTemplate.opsForValue().set(orderNo, map, 120,TimeUnit.MINUTES);
        log.info("生成微信支付二维码成功!");
        return map;
    }

    @Override
    public Map<String, String> getPayStatus(String orderNo) throws Exception {
        log.info("正在查询支付状态....");
        Order order = orderService.getOrderByOrderNo(orderNo);

        //1、封装参数
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", ConstantWxPayUtil.WX_PAY_APPID);
        paramsMap.put("mch_id", ConstantWxPayUtil.WX_PAY_MCH_ID);
        paramsMap.put("out_trade_no", orderNo);
        paramsMap.put("nonce_str", WXPayUtil.generateNonceStr());

        //调用httpclient 传递xml格式参数
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
        client.setXmlParam(WXPayUtil.generateSignedXml(paramsMap, ConstantWxPayUtil.WX_PAY_MCH_KEY));
        client.setHttps(true);
        client.post();
        String xml = client.getContent();
        Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
        log.info("用户支付状态："+resultMap.get("trade_state"));
        return resultMap;
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //获取订单号
        String orderNo = map.get("out_trade_no");

        Order order = orderService.getOrderByOrderNo(orderNo);
        if (order.getStatus() == 1) {return;}//若订单已支付直接返回
        order.setStatus(1);//订单状态设置为已支付
        orderService.updateById(order);

        //记录支付日志
        PayLog payLog = new PayLog();
        BeanUtils.copyProperties(order, payLog);
        payLog.setPayTime(new Date());//订单完成时间
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));//支付流水号
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);
    }

}

