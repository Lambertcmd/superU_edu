package com.geek.edustatistics.client;

import com.geek.commonutils.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName UcenterClientDegrade
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/22 18:55
 * @Version 1.0
 **/
@Component
@Slf4j
public class UcenterClientDegrade implements UcenterClient{

    @Override
    public R getRegisterCount(String date) {
        log.info("服务降级：远程调用getRegisterCount出错");
        return R.error();
    }
}
