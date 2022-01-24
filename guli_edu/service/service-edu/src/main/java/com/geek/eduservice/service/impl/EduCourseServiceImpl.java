package com.geek.eduservice.service.impl;

import com.geek.eduservice.entity.EduCourse;
import com.geek.eduservice.entity.EduCourseDescription;
import com.geek.eduservice.entity.vo.CourseInfoVo;
import com.geek.eduservice.mapper.EduCourseMapper;
import com.geek.eduservice.service.EduCourseDescriptionService;
import com.geek.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            //添加失败
            throw new GuliException(20001, "添加课程信息失败");
        }
        String courseId = eduCourse.getId();
        //2.向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseId);
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.save(courseDescription);
        return courseId;
        //3.
    }
}
