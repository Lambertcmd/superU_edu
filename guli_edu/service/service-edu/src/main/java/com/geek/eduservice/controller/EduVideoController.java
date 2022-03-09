package com.geek.eduservice.controller;


import com.geek.commonutils.R;
import com.geek.eduservice.entity.EduVideo;
import com.geek.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
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

    @ApiOperation("添加小节")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //TODO 后续该方法需要完善(删除小节时，同时视频也需要删除)
    @ApiOperation("删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable("id") String id){
        videoService.removeById(id);
        return R.ok();
    }

    @ApiOperation("根据小节id查询小节")
    @GetMapping("getVideoById/{id}")
    public R getVideoById(@PathVariable("id") String id){
        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }

    @ApiOperation("修改小节信息")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo video){
        videoService.updateById(video);
        return R.ok();
    }
}

