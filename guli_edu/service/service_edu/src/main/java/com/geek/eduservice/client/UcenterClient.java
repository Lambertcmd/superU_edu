package com.geek.eduservice.client;

import com.geek.commonutils.dto.CommentMemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientDegrade.class)
public interface UcenterClient {
    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/educenter/member/getInfoById/{id}")
    CommentMemberInfo getInfoById(@PathVariable("id") String id);
}
