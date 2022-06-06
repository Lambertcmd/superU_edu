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
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //时间降序排序(最新添加的排最前)
        queryWrapper.orderByDesc("gmt_create");
        Page<EduTeacher> page = baseMapper.selectPage(teacherPage, queryWrapper);
        log.info("page,{}",page.getRecords());
    }

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        //把分页数据封装到pageTeacher对象
        baseMapper.selectPage(pageTeacher, wrapper);
        //将分页数据取出来
        List<EduTeacher> records = pageTeacher.getRecords();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean hasNext = pageTeacher.hasNext();//是否有下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//是否有上一页

        //将分页数据取出来放到map集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
