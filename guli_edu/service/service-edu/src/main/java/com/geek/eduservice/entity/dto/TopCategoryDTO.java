package com.geek.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TopCategory
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/10 18:46
 * @Version 1.0
 **/
@Data
@ApiModel(value = "TopCategoryDTO",description = "一级课程分类封装对象")
public class TopCategoryDTO {

    @ApiModelProperty(value = "一级课程分类id")
    private String id;

    @ApiModelProperty(value = "一级课程分类名称")
    private String title;

    @ApiModelProperty(value = "该一级课程分类下的二级分类集合")
    private List<SecondCategoryDTO> children = new ArrayList<>();
}
