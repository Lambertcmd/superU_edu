package com.geek.eduorder.service.impl;

import com.geek.commonutils.dto.CommentMemberInfo;
import com.geek.eduorder.client.UcenterClient;
import com.geek.eduorder.entity.Order;
import com.geek.eduorder.mapper.OrderMapper;
import com.geek.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.eduorder.utils.OrderNoUtil;
import com.geek.eduorder.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(OrderVo orderVo, String memberId) {
        //需要远程调用根据用户id获取用户信息
        CommentMemberInfo commentMemberInfo = ucenterClient.getInfoById(memberId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        BeanUtils.copyProperties(orderVo, order);
        BeanUtils.copyProperties(commentMemberInfo, order);
        order.setPayType(1);//支付类型/支付方式
        order.setStatus(0);//订单状态
        baseMapper.insert(order);
        log.info("order:"+order);
        return order.getOrderNo();
    }
}
