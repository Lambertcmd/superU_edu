package com.geek.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduservice.entity.frontdto.CourseInfoDTO;
import com.geek.eduservice.entity.frontvo.CourseQueryFrontVo;
import com.geek.eduservice.entity.vo.CourseInfoVo;
import com.geek.eduservice.entity.vo.CoursePublishVo;
import com.geek.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

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
    void removeCourseById(String courseId);

    void pageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery);

    List<EduCourse> getByTeacherId(String id);

    Map<String,Object> getCourseListPage(Page<EduCourse> coursePage, CourseQueryFrontVo courseQueryVo);

    /**
     * 根据课程id查询课程详情页信息
     * @param courseId
     * @return
     */
    CourseInfoDTO getBaseCourseInfo(String courseId);

    void updateCourseViewCount(String courseId);
}
