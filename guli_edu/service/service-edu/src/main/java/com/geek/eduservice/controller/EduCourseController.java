package com.geek.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.result.R;
import com.geek.eduservice.entity.EduCourse;
import com.geek.eduservice.entity.vo.CourseInfoVo;
import com.geek.eduservice.entity.vo.CoursePublishVo;
import com.geek.eduservice.entity.vo.CourseQuery;
import com.geek.eduservice.service.EduCourseService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@RestController
@Slf4j
@Api(tags = "课程管理")
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

//    @ApiOperation("课程列表")
//    @GetMapping
//    public R getCourseList() {
//        List<EduCourse> list = courseService.list();
//        return R.ok().data("list", list);
//    }

    /**
     * 分页复杂查询讲师
     * @param page        当前页
     * @param size        每页大小
     * @param courseQuery 接收前端传来的课程查询条件
     * @return
     */
    @PostMapping("/pageCourseCondition/{page}/{size}")
    @ApiOperation("分页复杂查询课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页"),
            @ApiImplicitParam(name = "size",value = "每页大小")
    })
    public R pageCourseCondition(@PathVariable("page") long page, @PathVariable("size") long size,
                                  @RequestBody(required = false) CourseQuery courseQuery){
        //创建page
        Page<EduCourse> coursePage = new Page<>(page,size);
        //构建查询条件
        courseService.pageQuery(coursePage, courseQuery);
        //调用方法实现复杂查询分页
        long total = coursePage.getTotal();//数据量
        List<EduCourse> records = coursePage.getRecords();//当前页的内容
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("删除课程")
    @ApiImplicitParam(name = "id", value = "课程id")
    @DeleteMapping("{id}")
    public R deleteCourse(@PathVariable("id") String courseId) {
        courseService.removeCourseById(courseId);
        return R.ok();
    }

    @ApiOperation("添加课程基本信息")
    @ApiImplicitParam(name = "courseInfoVo", value = "课程基本信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加成功后的课程id
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    @ApiOperation("根据课程id查询课程基本信息")
    @ApiImplicitParam(name = "courseId", value = "课程id")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @ApiOperation("修改课程基本信息")
    @ApiImplicitParam(name = "courseInfoVo",value = "课程基本信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @ApiOperation("根据课程id查询课程确认信息")
    @ApiImplicitParam(name = "courseId",value = "课程id")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable("id") String courseId) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(courseId);
        log.info("result:{}",R.ok().data("coursePublish",coursePublishVo));
        return R.ok().data("coursePublish", coursePublishVo);
    }

    @ApiOperation("课程最终发布")
    @ApiImplicitParam(name = "value",value = "课程id")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable("id") String courseId) {
        courseService.publishCourse(courseId);
        return R.ok();
    }
}

