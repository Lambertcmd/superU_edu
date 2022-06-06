package com.geek.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author Lambert
 * @since 2022-01-07
 */
@Data
@TableName("edu_subject")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "EduSubject对象", description = "课程科目")
public class EduSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程类别ID")
    private String id;

    @ApiModelProperty("类别名称")
    private String title;

    @ApiModelProperty("父ID")
    private String parentId;

    @ApiModelProperty("排序字段")
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date gmtModified;

}
