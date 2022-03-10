package com.geek.eduservice.service;

import com.geek.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduservice.entity.vo.CourseInfoVo;
import com.geek.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程基本信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //查询课程确认信息
    CoursePublishVo publishCourseInfo(String courseId);

    //课程最终发布
    void publishCourse(String courseId);

    //删除课程
    void removeCourse(String courseId);
}
