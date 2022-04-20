package com.geek.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName SecondCategory
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/10 18:47
 * @Version 1.0
 **/
@Data
@ApiModel(value = "SecondCategoryDTO",description = "二级课程分类封装对象")
public class SecondCategoryDTO {

    @ApiModelProperty(value = "二级课程分类id")
    private String id;

    @ApiModelProperty(value = "二级课程分类名称")
    private String title;
}
