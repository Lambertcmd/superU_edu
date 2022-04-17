package com.geek.educenter.controller;


import com.geek.commonutils.JwtUtils;
import com.geek.commonutils.R;
import com.geek.educenter.entity.UcenterMember;
import com.geek.educenter.entity.vo.LoginVo;
import com.geek.educenter.entity.vo.RegisterVo;
import com.geek.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-12
 */
@CrossOrigin
@RestController
@Slf4j
@Api(tags = "用户模块")
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;


    @ApiOperation("用户登录")
    @PostMapping("login")
    public R loginUser(@RequestBody LoginVo loginVo){
        //返回token值，使用jwt生成token
        String token = memberService.login(loginVo);
        return R.ok().data("token",token);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public R registerUser(@RequestBody @Valid RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("getUserInfo")
    public R getUserInfo(HttpServletRequest request){
        //调用jwt工具类  根据头信息的token获取用户Id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //根据用户id查询用户
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}

