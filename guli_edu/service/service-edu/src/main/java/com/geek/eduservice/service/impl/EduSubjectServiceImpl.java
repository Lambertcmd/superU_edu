package com.geek.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.geek.eduservice.entity.EduSubject;
import com.geek.eduservice.entity.category.SecondCategory;
import com.geek.eduservice.entity.category.TopCategory;
import com.geek.eduservice.entity.excel.SubjectData;
import com.geek.eduservice.listener.SubjectExcelListener;
import com.geek.eduservice.mapper.EduSubjectMapper;
import com.geek.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper subjectMapper;

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try{
            log.info("file:"+file.getOriginalFilename());
            //1.获取文件输入流
            InputStream inputStream = file.getInputStream();
            //2.调用方法进行读取文件
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet("课程分类").doRead();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<TopCategory> getAllSubject() {
        //查询一级分类(parentId=0)
        QueryWrapper<EduSubject> wrapperTop = new QueryWrapper<>();
        wrapperTop.eq("parent_id", "0");
        List<EduSubject> topSubjectList = baseMapper.selectList(wrapperTop);
        //查询二级分类(parentId!=0)
        QueryWrapper<EduSubject> secondCategoryWrapper = new QueryWrapper<>();
        secondCategoryWrapper.ne("parent_id", "0");
        List<EduSubject> secondSubjectList = baseMapper.selectList(secondCategoryWrapper);
        //创建list集合 用于存储最终封装的数据
        List<TopCategory> categoryList = new ArrayList<>();
        //封装一级分类
        topSubjectList.forEach(topSubject -> {
            TopCategory topCategory = new TopCategory();
            //复制一级分类中名称相同的属性(id,title)
            BeanUtils.copyProperties(topSubject, topCategory);
            //封装二级分类
            List<SecondCategory> secondCategoryList = new ArrayList<>();
            secondSubjectList.forEach(secondSubject -> {
                if (StringUtils.equals(secondSubject.getParentId(), topCategory.getId())){
                    SecondCategory secondCategory = new SecondCategory();
                    BeanUtils.copyProperties(secondSubject, secondCategory);
                    secondCategoryList.add(secondCategory);
                }
            });
            topCategory.setChildren(secondCategoryList);
            categoryList.add(topCategory);
        });
        return categoryList;
    }
}
