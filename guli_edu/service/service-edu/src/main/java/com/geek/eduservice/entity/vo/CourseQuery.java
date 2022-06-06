package com.geek.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseQuery
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/11 17:39
 * @Version 1.0
 **/
@Data
@ApiModel(value = "CourseQuery",description = "课程复杂查询条件对象封装")
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程状态",example = "Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "查询课程发布时间区间，开始时间",example = "2019-01-01 10:10:10")
    private String begin;//注意 这里使用String类型，前端 传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询课程发布时间区间，结束时间",example = "2019-01-01 10:10:10")
    private String end;

}
