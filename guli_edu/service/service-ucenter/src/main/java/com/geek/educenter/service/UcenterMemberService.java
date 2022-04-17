package com.geek.educenter.service;

import com.geek.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.educenter.entity.vo.LoginVo;
import com.geek.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-04-12
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

    /**
     * 用户注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    UcenterMember getByOpenId(String openid);
}
