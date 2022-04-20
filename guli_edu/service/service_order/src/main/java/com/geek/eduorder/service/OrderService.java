package com.geek.eduorder.service;

import com.geek.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduorder.vo.OrderVo;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     * @param orderVo
     * @param memberId
     * @return
     */
    String createOrder(OrderVo orderVo, String memberId);
}
