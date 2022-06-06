package com.geek.eduservice.mapper;

import com.geek.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geek.eduservice.entity.frontdto.CourseInfoDTO;
import com.geek.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Lambert
 * @since 2022-01-23
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 根据课程id查询课程详情页信息
     * @param courseId
     * @return
     */
    CourseInfoDTO getBaseCourseInfo(String courseId);
}
