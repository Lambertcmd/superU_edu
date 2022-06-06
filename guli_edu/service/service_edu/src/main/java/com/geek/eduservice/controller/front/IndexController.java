package com.geek.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geek.commonutils.result.R;
import com.geek.eduservice.entity.EduCourse;
import com.geek.eduservice.entity.EduTeacher;
import com.geek.eduservice.service.EduCourseService;
import com.geek.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/24 12:15
 * @Version 1.0
 **/
@Api(tags = "前台主页前端控制器")
@RestController
@RequestMapping("/eduservice/index")
public class IndexController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @Cacheable(value = "index",key = "'getIndexTeacherCourseList'")
    @ApiOperation("查询前台主页显示前8条热门课程，查询前4条名师")
    @GetMapping("/getIndexData")
    public R getIndexTeacherCourseList(){
        //查询前八条热门课程
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id")
                .last("limit 8");
        List<EduCourse> courseList = courseService.list(courseWrapper);

        //查询前四条名师
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id")
                .last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);
        return R.ok().data("courseList", courseList).data("teacherList",teacherList);
    }

}
