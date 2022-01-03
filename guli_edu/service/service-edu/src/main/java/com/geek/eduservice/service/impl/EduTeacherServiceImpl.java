package com.geek.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduservice.entity.EduTeacher;
import com.geek.eduservice.entity.vo.TeacherQuery;
import com.geek.eduservice.mapper.EduTeacherMapper;
import com.geek.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2021-12-27
 */
@Slf4j
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> teacherPage, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件是否为空，如果不为空拼接条件
        if (!StringUtils.isBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (level != null) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isBlank(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isBlank(end)) {
            queryWrapper.le("gmt_create", end);
        }
        Page<EduTeacher> page = baseMapper.selectPage(teacherPage, queryWrapper);
        log.info("page,{}",page.getRecords());
    }
}
