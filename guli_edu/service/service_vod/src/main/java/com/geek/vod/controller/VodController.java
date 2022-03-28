package com.geek.vod.controller;

import com.geek.commonutils.R;
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
        return vodService.removeVideoById(id);
    }


    @ApiOperation("批量删除云端视频")
    @ApiImplicitParam(name = "videoSourceIdList",value = "云端视频id集合")
    @DeleteMapping("/deleteBatchVideo")
    public R deleteBatchVideo(@RequestParam("videoSourceIdList") List<String> videoSourceIdList){
        return vodService.deleteBatchVideo(videoSourceIdList);
    }
}
