package com.geek.eduorder.controller;


import com.geek.commonutils.jwt.JwtUtils;
import com.geek.commonutils.result.R;
import com.geek.eduorder.service.OrderService;
import com.geek.eduorder.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "订单功能")
@RequestMapping("/eduorder/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("/createOrder")
    public R createOrder(@RequestBody OrderVo orderVo, HttpServletRequest request){
        //创建订单 返回订单号
        String orderNo = orderService.createOrder(orderVo, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderNo", orderNo);
    }
}

