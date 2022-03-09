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

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String courseId);
}
