package com.geek.eduorder.service;

import com.geek.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
public interface PayService extends IService<PayLog> {

    /**
     * 生成微信支付二维码
     * @param orderNo
     * @return
     */
    Map<String, Object> createNative(String orderNo) throws Exception;

    /**
     * 查询支付状态
     * @param orderNo
     * @return
     * @throws Exception
     */
    Map<String, String> getPayStatus(String orderNo) throws Exception;

    /**
     * 支付成功时 修改订单状态
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);
}
