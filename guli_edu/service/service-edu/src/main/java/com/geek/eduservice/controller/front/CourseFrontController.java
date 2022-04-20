package com.geek.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.commonutils.result.R;
import com.geek.eduservice.entity.EduCourse;
import com.geek.eduservice.entity.dto.ChapterDTO;
import com.geek.eduservice.entity.frontdto.CourseInfoDTO;
import com.geek.eduservice.entity.frontvo.CourseQueryFrontVo;
import com.geek.eduservice.service.EduChapterService;
import com.geek.eduservice.service.EduCourseService;
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
 * @ClassName CourseFrontController
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/16 22:33
 * @Version 1.0
 **/
@RestController
@Slf4j
@CrossOrigin
@Api(tags = "前台课程模块接口")
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation("分页复杂查询课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码"),
            @ApiImplicitParam(name = "limit",value = "每页大小"),
            @ApiImplicitParam(name = "courseQueryVo",value = "查询条件封装对象")
    })
    @PostMapping("{page}/{limit}")
    public R pageCourseList(@PathVariable("page") long page,
                            @PathVariable("limit") long limit,
                            @RequestBody(required = false) CourseQueryFrontVo courseQueryVo){
        Page<EduCourse> coursePage = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseListPage(coursePage, courseQueryVo);
        return R.ok().data(map);
    }

    @ApiOperation("根据课程id查询课程详情页")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable("courseId") String courseId){
        //根据课程id 多表查询sql语句查询信息
        CourseInfoDTO courseInfoDTO = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询课程大纲(章节和小节)
        List<ChapterDTO> chapterVoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseInfoDTO", courseInfoDTO).data("chapterVoList",chapterVoList);
    }

}
