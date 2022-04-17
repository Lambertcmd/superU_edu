package com.geek.educms.service;

import com.geek.educms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-03-23
 */
public interface BannerService extends IService<Banner> {

    /**
     * 查询所有banner
     * @return
     */
    List<Banner> getAllBanner();
}
