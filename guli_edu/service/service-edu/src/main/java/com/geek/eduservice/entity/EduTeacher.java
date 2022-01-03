package com.geek.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author Lambert
 * @since 2021-12-27
 */
@Getter
@Setter
@TableName("edu_teacher")
@ApiModel(value = "EduTeacher对象", description = "讲师")
public class EduTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("讲师ID")
    private String id;

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty("讲师简介")
    private String intro;

    @ApiModelProperty("讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty("讲师头像")
    private String avatar;

    @ApiModelProperty("排序")
    private Integer sort;

    @TableLogic//逻辑删除注解
    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)//插入时自动填充
    @ApiModelProperty(value = "创建时间",example = "2019-01-01 8:00:00")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)//修改和插入时自动填充
    @ApiModelProperty(value = "更新时间",example = "2019-01-01 8:00:00")
    private Date gmtModified;


}
