package com.geek.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.geek.eduservice.entity.EduSubject;
import com.geek.eduservice.entity.excel.SubjectData;
import com.geek.eduservice.listener.SubjectExcelListener;
import com.geek.eduservice.mapper.EduSubjectMapper;
import com.geek.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file) {
        try{
            //1.获取文件输入流
            InputStream inputStream = file.getInputStream();
            //2.调用方法进行读取文件
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener()).sheet("课程分类").doRead();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
