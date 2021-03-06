package com.geek.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.acl.dto.UserBaseInfo;
import com.geek.acl.entity.User;
import com.geek.acl.mapper.UserMapper;
import com.geek.acl.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public UserBaseInfo getBaseInfo(String userId) {
        User user = baseMapper.selectById(userId);
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        BeanUtils.copyProperties(user, userBaseInfo);
        return userBaseInfo;
    }
}
