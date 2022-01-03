package com.geek.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName TeacherQuery
 * @Description TODO
 * @Author Lambert
 * @Date 2021/12/28 14:18
 * @Version 1.0
 **/
@Data
@ApiModel(value = "Teacher复杂查询vo",description = "讲师复杂查询对象封装")
public class TeacherQuery  {

    @ApiModelProperty(value = "讲师名称")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询注册时间区间，开始时间",example = "2019-01-01 10:10:10")
    private String begin;//注意 这里使用String类型，前端 传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询注册时间区间，结束时间",example = "2019-01-01 10:10:10")
    private String end;

}
