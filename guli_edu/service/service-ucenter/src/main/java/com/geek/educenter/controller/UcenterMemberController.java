package com.geek.educenter.controller;


import com.geek.commonutils.dto.CommentMemberInfo;
import com.geek.commonutils.jwt.JwtUtils;
import com.geek.commonutils.result.R;
import com.geek.educenter.entity.UcenterMember;
import com.geek.educenter.entity.vo.LoginVo;
import com.geek.educenter.entity.vo.RegisterVo;
import com.geek.educenter.service.UcenterMemberService;
import com.geek.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-04-12
 */
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
        log.info("用户登录：{}",loginVo.getMobile());
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

    @ApiOperation("根据用户id获取用户信息")
    @GetMapping("getInfoById/{id}")
    public CommentMemberInfo getInfoById(@PathVariable("id") String id){
        UcenterMember member = Optional.ofNullable(memberService.getById(id))
                .orElseThrow(() -> new GuliException(20001, "用户不存在"));
        CommentMemberInfo commentMemberInfo = new CommentMemberInfo();
        BeanUtils.copyProperties(member, commentMemberInfo);
        commentMemberInfo.setMemberId(member.getId());
        return commentMemberInfo;
    }

    @ApiOperation("统计某日注册人数")
    @GetMapping("/getRegisterCount/{date}")
    public R getRegisterCount(@PathVariable("date") String date){
        Integer count = memberService.getRegisterCount(date);
        log.info("{}注册人数:{}",date,count);
        return R.ok().data("RegisterCount",count);
    }

}

