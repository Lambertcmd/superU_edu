package com.geek.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName VideoVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/1 18:15
 * @Version 1.0
 **/
@Data
@ApiModel(value = "VideoDTO",description = "小节封装对象")
public class VideoDTO {
    @ApiModelProperty(value = "小节id")
    private String id;

    @ApiModelProperty(value = "小节名称")
    private String title;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Integer isFree;

    @ApiModelProperty("云端视频资源id")
    private String videoSourceId;

}
