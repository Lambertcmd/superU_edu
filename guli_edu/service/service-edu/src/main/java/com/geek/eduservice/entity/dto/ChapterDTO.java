package com.geek.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/1 18:15
 * @Version 1.0
 **/
@ApiModel(value = "ChapterDTO",description = "章节封装对象")
@Data
public class ChapterDTO {

    @ApiModelProperty(value = "章节id")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "该章节下的小节集合")
    private List<VideoDTO> children = new ArrayList<>();
}
