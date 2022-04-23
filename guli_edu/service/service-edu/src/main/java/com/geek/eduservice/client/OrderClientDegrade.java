package com.geek.eduservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderClientDegrade
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/22 13:24
 * @Version 1.0
 **/
@Component
@Slf4j
public class OrderClientDegrade implements OrderClient {

    @Override
    public boolean getIsBuyCourse(String memberId, String courseId) {
        log.info("服务降级：调用getIsBuyCourse接口出错");
        return false;
    }
}
