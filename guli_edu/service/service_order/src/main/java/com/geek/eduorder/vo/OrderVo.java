package com.geek.eduorder.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName orderVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/21 1:25
 * @Version 1.0
 **/
@Data
@ApiModel(value = "OrderVo",description = "创建订单所需要的课程参数实体")
public class OrderVo implements Serializable {
    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    @ApiModelProperty(value = "课程封面图片路径")
    private String courseCover;

    @ApiModelProperty(value = "订单价格")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "讲师名称")
    private String teacherName;


}
