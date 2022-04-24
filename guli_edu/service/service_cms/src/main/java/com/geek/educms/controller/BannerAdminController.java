package com.geek.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.result.R;
import com.geek.educms.entity.Banner;
import com.geek.educms.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台管理banner前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-03-23
 */
@RestController
@Slf4j
@Api(tags = "后台轮播管理")
@RequestMapping("/educms/bannerAdmin")
public class BannerAdminController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("pageBanner/{page}/{size}")
    @ApiOperation("分页显示banner列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true)
    })
    public R pageBanner(@PathVariable("page") long page, @PathVariable("size") long size) {
        Page<Banner> bannerPage = new Page<>(page, size);
        bannerService.page(bannerPage);
        if (bannerPage.getTotal() == 0) {
            return R.ok().message("没有查到轮播图数据");
        }
        return R.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }

    @PostMapping("/addBanner")
    @ApiOperation("新增banner")
    public R addBanner(@RequestBody Banner banner){
        boolean result = bannerService.save(banner);
        if (!result) {
            return R.error().message("新增banner出错");
        }
        return R.ok();
    }

    @GetMapping("/getBannerById/{id}")
    @ApiOperation("根据id获取banner")
    @ApiImplicitParam(name = "id",value = "bannerId")
    public R getBannerById(@PathVariable("id") String id){
        Banner banner = bannerService.getById(id);
        if (banner == null) {
            return R.error().message("没有找到对应的banner");
        }
        return R.ok().data("item",banner);
    }

    @PostMapping("updateBanner")
    @ApiOperation("修改banner")
    public R updateBanner(@RequestBody Banner banner){
        boolean result = bannerService.updateById(banner);
        if (!result){
            return R.error().message("修改banner出错");
        }
        return R.ok();
    }

    @DeleteMapping("remove/{id}")
    @ApiOperation("删除banner")
    public R removeBanner(@PathVariable("id") String id){
        boolean result = bannerService.removeById(id);
        if (!result){
            return R.error().message("删除banner出错");
        }
        return R.ok();
    }
}

