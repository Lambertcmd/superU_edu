package com.geek.eduservice.mapper;

import com.geek.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {
}
