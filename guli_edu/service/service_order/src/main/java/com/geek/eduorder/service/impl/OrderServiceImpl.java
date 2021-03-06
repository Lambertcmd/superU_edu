package com.geek.eduorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geek.commonutils.dto.CommentMemberInfo;
import com.geek.eduorder.client.UcenterClient;
import com.geek.eduorder.entity.Order;
import com.geek.eduorder.mapper.OrderMapper;
import com.geek.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.eduorder.utils.OrderNoUtil;
import com.geek.eduorder.vo.OrderVo;
import com.geek.servicebase.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        log.info("order:" + order);
        return order.getOrderNo();
    }

    @Override
    public Order getOrderByOrderNo(String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        Order order = Optional.ofNullable(baseMapper.selectOne(queryWrapper))
                .orElseThrow(() -> new GuliException(20001, "订单不存在"));
        return order;
    }

    @Override
    public boolean getIsBuyCourse(String memberId, String courseId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId)
                .eq("course_id", courseId)
                .eq("status", 1);
        Long count = baseMapper.selectCount(queryWrapper);
        log.info("查询结果："+count);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }


}
