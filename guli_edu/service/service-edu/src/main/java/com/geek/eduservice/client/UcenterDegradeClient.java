package com.geek.eduservice.client;

import com.geek.commonutils.dto.CommentMemberInfo;
import com.geek.commonutils.result.R;
import com.geek.servicebase.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName UcenterDegradeClient
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/20 14:04
 * @Version 1.0
 **/
@Component
@Slf4j
public class UcenterDegradeClient implements UcenterClient {

    @Override
    public CommentMemberInfo getInfoById(String id) {
        log.info("服务降级：获取添加评论的用户信息出错，用户id：" + id);
        return null;
    }
}
