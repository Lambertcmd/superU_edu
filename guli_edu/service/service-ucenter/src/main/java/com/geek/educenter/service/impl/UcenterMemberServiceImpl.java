package com.geek.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.commonutils.jwt.JwtUtils;
import com.geek.commonutils.utils.MD5;
import com.geek.commonutils.redis.RedisUtils;
import com.geek.educenter.entity.UcenterMember;
import com.geek.educenter.entity.vo.LoginVo;
import com.geek.educenter.entity.vo.RegisterVo;
import com.geek.educenter.mapper.UcenterMemberMapper;
import com.geek.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-12
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisUtils redisUtils;

    public String login(LoginVo loginVo) {
        //获取登录用户的手机号和密码
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //手机号和密码非空判断
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
            throw new GuliException(20001, "手机号或密码为空");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> mobileQueryWrapper = new QueryWrapper<>();
        mobileQueryWrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(mobileQueryWrapper);
        if (mobileMember == null) {
            throw new GuliException(20001, "手机号不存在");
        }

        //判断密码是否正确
        //由于存储到数据库的密码必须加密，需要将输入的密码进行加密，再和数据库密码进行比较
        //加密方式采用MD5(MD5加密算法不可逆)
        if (!StringUtils.equals(MD5.encrypt(password),mobileMember.getPassword())){
            throw new GuliException(20001, "用户密码错误");
        }

        //判断用户是否禁用
        if (mobileMember.getIsDisabled() == 1){
            throw new GuliException(20001, "用户被禁用");
        }

        //登录成功
        //使用jwt工具类 生成token字符串
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息
        String code = registerVo.getCode();//验证码
        String mobile = registerVo.getMobile();//手机号码
        String nickname = registerVo.getNickname();//昵称
        String password = MD5.encrypt(registerVo.getPassword());//密码加密

        //判断redis的验证码和用户输入的验证码是否一致
        String redisCode = (String) redisUtils.get(mobile);
        if (StringUtils.isBlank(redisCode)){
            throw new GuliException(20001, "验证码已过期");
        }else if (!StringUtils.equals(code, redisCode)){
            throw new GuliException(20001, "验证码不正确");
        }

        //判断手机号是否重复
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Long count = baseMapper.selectCount(queryWrapper);
        //表里有重复的手机号
        if (count > 0) {
            throw new GuliException(20001, "该手机号已被注册");
        }
        //以上都没问题可以注册账户了
        UcenterMember member = new UcenterMember();
        BeanUtils.copyProperties(registerVo, member);
        member.setPassword(password);
        member.setAvatar("https://guli-file--upload.oss-cn-shenzhen.aliyuncs.com/" +
                         "2022/01/06/87530d443bbc446988bdf9033e63d84bfile.png");
        baseMapper.insert(member);
    }

    @Override
    public UcenterMember getByOpenId(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public Integer getRegisterCount(String date) {
        return baseMapper.selectRegisterCount(date);
    }
}
