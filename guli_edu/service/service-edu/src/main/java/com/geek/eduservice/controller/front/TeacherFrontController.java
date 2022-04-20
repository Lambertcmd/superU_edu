package com.geek.eduservice.controller.front;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.result.R;
import com.geek.eduservice.entity.EduCourse;
import com.geek.eduservice.entity.EduTeacher;
import com.geek.eduservice.service.EduCourseService;
import com.geek.eduservice.service.EduTeacherService;
import com.geek.servicebase.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherFrontController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/16 15:34
 * @Version 1.0
 **/
@RestController
@Slf4j
@CrossOrigin
@Api(tags = "前台讲师模块接口")
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation("分页查询前台讲师模块数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页"),
            @ApiImplicitParam(name = "limit",value = "每页大小")
    })
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable("page") long page,
                                 @PathVariable("limit") long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        //返回分页内的所有数据不止返回total和records
        return R.ok().data(map);
    }

    @ApiOperation("根据讲师Id查询讲师和课程")
    @GetMapping("getTeacherFrontInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable("id") String id){
        if (StringUtils.isBlank(id)){
            throw new GuliException(20001, "id为空");
        }
        //查询讲师信息
        EduTeacher teacher = teacherService.getById(id);
        //根据讲师id查询讲师的课程列表
        List<EduCourse> courseList = courseService.getByTeacherId(id);
        log.info("courseList"+courseList);
        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
