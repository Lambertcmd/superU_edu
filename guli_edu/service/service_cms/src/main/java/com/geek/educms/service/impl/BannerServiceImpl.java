package com.geek.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geek.educms.entity.Banner;
import com.geek.educms.mapper.BannerMapper;
import com.geek.educms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bouncycastle.jce.interfaces.ECKey;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-03-23
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<Banner> getAllBanner() {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id")
                .last("limit 2");
        List<Banner> bannerList = baseMapper.selectList(wrapper);
        return bannerList;
    }
}
