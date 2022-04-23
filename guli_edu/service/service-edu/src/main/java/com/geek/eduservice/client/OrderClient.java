package com.geek.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-order", fallback = OrderClientDegrade.class)
public interface OrderClient {

    /**
     * 查询用户是否购买课程
     *
     * @param memberId
     * @param courseId
     * @return
     */
    @GetMapping("/eduorder/order/getIsBuyCourse/{memberId}/{courseId}")
    boolean getIsBuyCourse(@PathVariable("memberId") String memberId,
                           @PathVariable("courseId") String courseId);
}
