package com.geek.eduorder.client;

import com.geek.commonutils.dto.CommentMemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/educenter/member/getInfoById/{id}")
    CommentMemberInfo getInfoById(@PathVariable("id") String id);
}
