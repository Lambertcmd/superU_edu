package com.geek.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CoursePublishVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/9 21:49
 * @Version 1.0
 **/
@Data
public class CoursePublishVo implements Serializable {
    public static final long serialVersionUID = 1L;

    private String id;//课程id

    private String title;//课程名称

    private String cover;//课程封面图片路径

    private Integer lessonNum;//总课时

    private String subjectLevelOne;//课程一级分类

    private String subjectLevelTwo;//课程耳机分类

    private String teacherName;//讲师名

    private String price;//课程价格

}
