package com.geek.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.commonutils.jwt.JwtUtils;
import com.geek.commonutils.result.R;
import com.geek.eduorder.entity.Order;
import com.geek.eduorder.service.OrderService;
import com.geek.eduorder.vo.OrderVo;
import com.geek.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@RestController
@Slf4j
@Api(tags = "订单功能")
@RequestMapping("/eduorder/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("/createOrder")
    public R createOrder(@RequestBody OrderVo orderVo, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isBlank(memberId)){
            return R.error().message("请先登录！");
        }
        //创建订单 返回订单号
        String orderNo = orderService.createOrder(orderVo, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderNo", orderNo);
    }

    @ApiOperation("根据订单号查询订单信息")
    @GetMapping("/getOrderByOrderNo/{orderNo}")
    public R getOrderByOrderNo(@PathVariable("orderNo") String orderNo){
        Order order = orderService.getOrderByOrderNo(orderNo);
        return R.ok().data("order", order);
    }

    @GetMapping("/getIsBuyCourse/{memberId}/{courseId}")
    public boolean getIsBuyCourse(@PathVariable("memberId") String memberId,
                                  @PathVariable("courseId") String courseId){
        return orderService.getIsBuyCourse(memberId,courseId);
    }
}

