package com.geek.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geek.eduservice.entity.EduCourse;
import com.geek.eduservice.entity.EduCourseDescription;
import com.geek.eduservice.entity.frontdto.CourseInfoDTO;
import com.geek.eduservice.entity.frontvo.CourseQueryFrontVo;
import com.geek.eduservice.entity.vo.CourseInfoVo;
import com.geek.eduservice.entity.vo.CoursePublishVo;
import com.geek.eduservice.entity.vo.CourseQuery;
import com.geek.eduservice.mapper.EduCourseMapper;
import com.geek.eduservice.service.EduChapterService;
import com.geek.eduservice.service.EduCourseDescriptionService;
import com.geek.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.eduservice.service.EduVideoService;
import com.geek.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;



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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        //2.查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        int update = baseMapper.updateById(course);
        if (update  == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }
        //2.修改描述表
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String courseId) {
        //调用mapper查询数据库
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    @Override
    public void publishCourse(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        eduCourse.setStatus(EduCourse.COURSE_NORMAL);
        int isSuccess = baseMapper.updateById(eduCourse);
        if (isSuccess == 0) {
            throw new GuliException(20001, "课程发布失败");
        }
    }

    @Override
    public void removeCourseById(String courseId) {
        //1.根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);
        //2.根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);
        //3.根据课程id删除描述
        courseDescriptionService.removeById(courseId);
        //4.根据课程id删除课程
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new GuliException(20001, "课程删除失败");
        }
    }

    @Override
    public void pageQuery(Page<EduCourse> coursePage, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //取出查询条件
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        //判断条件是否为空，若为空则不加入条件查询
        if (!StringUtils.isBlank(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isBlank(status)){
            wrapper.eq("status", status);
        }
        if (!StringUtils.isBlank(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isBlank(end)) {
            wrapper.le("gmt_create", end);
        }
        //时间降序排序(最新添加的排最前)
        wrapper.orderByDesc("gmt_create");
        Page<EduCourse> page = baseMapper.selectPage(coursePage, wrapper);
    }

    @Override
    public List<EduCourse> getByTeacherId(String id) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        wrapper.orderByDesc("gmt_modified");
        List<EduCourse> courseList = baseMapper.selectList(wrapper);
        return courseList;
    }

    @Override
    public Map<String, Object> getCourseListPage(Page<EduCourse> coursePage, CourseQueryFrontVo courseQueryVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        //查询条件
        if (StringUtils.isNotBlank(courseQueryVo.getSubjectParentId())){//一级分类
            queryWrapper.eq("subject_parent_id", courseQueryVo.getSubjectParentId());
        }
        if (StringUtils.isNotBlank(courseQueryVo.getSubjectId())){//二级分类
            queryWrapper.eq("subject_id", courseQueryVo.getSubjectId());
        }
        if (StringUtils.isNotBlank(courseQueryVo.getBuyCountSort())){//关注度/购买量降序
            queryWrapper.orderByDesc("buy_count");
        }
        if (StringUtils.isNotBlank(courseQueryVo.getGmtCreateSort())){//最新课程
            queryWrapper.orderByDesc("gmt_create");
        }
        if (StringUtils.isNotBlank(courseQueryVo.getPriceSort())){//价格降序
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(coursePage, queryWrapper);

        long total = coursePage.getTotal(); //课程总数量
        long size = coursePage.getSize();   //每页课程数量
        long current = coursePage.getCurrent();//当前页
        List<EduCourse> records = coursePage.getRecords();//当前页内容
        boolean hasNext = coursePage.hasNext();//是否有下一页
        long pages = coursePage.getPages();//总页数
        boolean hasPrevious = coursePage.hasPrevious();//是否有上一页

        HashMap<String, Object> map = new HashMap<>();

        map.put("total", total);
        map.put("size", size);
        map.put("current", current);
        map.put("records", records);
        map.put("pages", pages);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;


    }

    @Override
    public CourseInfoDTO getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

    @Override
    public void updateCourseViewCount(String courseId) {
        EduCourse course = baseMapper.selectById(courseId);
        //打开课程详情浏览量+1
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }
}
