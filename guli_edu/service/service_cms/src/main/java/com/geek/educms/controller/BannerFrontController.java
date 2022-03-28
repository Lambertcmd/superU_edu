package com.geek.educms.controller;

import com.geek.commonutils.R;
import com.geek.educms.entity.Banner;
import com.geek.educms.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BannerFrontController
 * @Description 前台banner前端控制器
 * @Author Lambert
 * @Date 2022/3/23 22:24
 * @Version 1.0
 **/
@RestController
@Slf4j
@CrossOrigin
@Api(tags = "前台轮播")
@RequestMapping("/educms/bannerFront")
public class BannerFrontController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/getAllBanner")
    @ApiOperation("获取首页banner")
    public R getAllBanner() {
        List<Banner> bannerList = bannerService.getAllBanner();
        if (bannerList.isEmpty()) {
            return R.error().message("查询无banner,请先添加");
        }
        return R.ok().data("bannerList", bannerList);
    }
}