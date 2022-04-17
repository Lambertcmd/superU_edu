package com.geek.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Lambert
 * @since 2021-12-27
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 分页查询后台管理系统讲师数据
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    /**
     * 分页查询前台讲师数据
     * @param pageTeacher
     * @return
     */
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
