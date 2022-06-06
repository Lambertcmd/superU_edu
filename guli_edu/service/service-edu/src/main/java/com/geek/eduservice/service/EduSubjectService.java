package com.geek.eduservice.service;

import com.geek.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.eduservice.entity.dto.TopCategoryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 从excel导入课程
     * @param file
     */
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<TopCategoryDTO> getAllSubject();
}
