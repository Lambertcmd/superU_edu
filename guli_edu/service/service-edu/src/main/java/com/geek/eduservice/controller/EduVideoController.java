package com.geek.eduservice.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.geek.commonutils.R;
import com.geek.eduservice.client.VodClient;
import com.geek.eduservice.entity.EduVideo;
import com.geek.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "小节管理")
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加小节")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        log.info("videoid"+eduVideo.getVideoSourceId());
        videoService.save(eduVideo);
        return R.ok();
    }

    @ApiOperation("删除小节")
    @ApiImplicitParam(name = "id",value = "小节id")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable("id") String id){
        return videoService.deleteVideo(id);
    }

    @ApiOperation("根据小节id查询小节")
    @ApiImplicitParam(name = "id",value = "小节id")
    @GetMapping("getVideoById/{id}")
    public R getVideoById(@PathVariable("id") String id){
        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }

    @ApiOperation("修改小节信息")
    @ApiImplicitParam(name = "video",value = "新的小节信息")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo video){
        videoService.updateById(video);
        return R.ok();
    }
}

