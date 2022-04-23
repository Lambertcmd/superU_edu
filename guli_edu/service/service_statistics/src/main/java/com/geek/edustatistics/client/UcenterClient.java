package com.geek.edustatistics.client;

import com.geek.commonutils.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientDegrade.class)
public interface UcenterClient {

    /**
     * 查询某日注册人数
     * @return
     */
    @GetMapping("/educenter/member/getRegisterCount/{date}")
    R getRegisterCount(@PathVariable("date") String date);
}
