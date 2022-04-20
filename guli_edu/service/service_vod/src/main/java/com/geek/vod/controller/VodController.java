package com.geek.vod.controller;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.commonutils.result.R;
import com.geek.servicebase.exception.GuliException;
import com.geek.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VodController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/19 0:59
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "阿里云视频点播服务")
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频到阿里云")
    @PostMapping("/uploadVideo")
    public R uploadVideo(@RequestPart("file") MultipartFile file){
        String videoSourceId = vodService.uploadVideo(file);
        return R.ok().data("videoSourceId",videoSourceId);
    }

    @ApiOperation("根据云端视频id删除云端视频")
    @ApiImplicitParam(name = "id",value = "云端视频id")
    @DeleteMapping("removeVideoSourceById/{id}")
    public R removeVideoSourceById(@PathVariable("id") String id){
        if (StringUtils.isBlank(id)) {
            throw new GuliException(20001, "视频id为空");
        }
        return vodService.removeVideoById(id);
    }


    @ApiOperation("批量删除云端视频")
    @ApiImplicitParam(name = "videoSourceIdList",value = "云端视频id集合")
    @DeleteMapping("/deleteBatchVideo")
    public R deleteBatchVideo(@RequestParam("videoSourceIdList") List<String> videoSourceIdList){
        return vodService.deleteBatchVideo(videoSourceIdList);
    }

    @ApiOperation("根据云端视频id获取视频播放凭证")
    @GetMapping("getPlayAuth/{videoId}")
    public R getPlayAuth(@PathVariable("videoId") String videoId) throws ClientException {
        if (StringUtils.isBlank(videoId)) {
            throw new GuliException(20001, "视频id为空");
        }
        String playAuth = vodService.getPlayAuth(videoId);
        return R.ok().data("playAuth", playAuth);
    }
}
