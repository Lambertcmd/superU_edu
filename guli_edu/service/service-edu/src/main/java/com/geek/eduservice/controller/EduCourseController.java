package com.geek.eduservice.controller;


import com.geek.commonutils.R;
import com.geek.eduservice.entity.vo.CourseInfoVo;
import com.geek.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@RestController
@Api(tags = "课程管理")
@CrossOrigin
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation("添加课程基本信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加成功后的课程id
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
}

