package com.geek.eduservice.controller;

import com.geek.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginEduController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/1 20:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("eduservice/user")
@CrossOrigin //解决跨域
@Slf4j
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        log.info("请求登录");
        return R.ok().data("token","admin-token");
    }

    @GetMapping("/info")
    public R info(){
        log.info("获取用户信息");
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("/logout")
    public R logout(){
        log.info("退出登录");
        return R.ok();
    }
}
