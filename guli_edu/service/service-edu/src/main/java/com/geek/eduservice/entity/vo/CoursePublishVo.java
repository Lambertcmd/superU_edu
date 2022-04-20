package com.geek.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "CoursePublishVo",description = "课程确认信息对象")
public class CoursePublishVo implements Serializable {
    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    private String id;//课程id

    @ApiModelProperty(value = "课程名称")
    private String title;//课程名称

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;//课程封面图片路径

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;//总课时

    @ApiModelProperty(value = "课程一级分类")
    private String subjectLevelOne;//课程一级分类

    @ApiModelProperty(value = "课程二级分类")
    private String subjectLevelTwo;//课程二级分类

    @ApiModelProperty(value = "讲师名")
    private String teacherName;//讲师名

    @ApiModelProperty(value = "课程价格")
    private String price;//课程价格

}
