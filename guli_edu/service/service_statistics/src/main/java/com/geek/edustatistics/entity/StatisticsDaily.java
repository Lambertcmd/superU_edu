package com.geek.edustatistics.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 网站统计日数据
 * </p>
 *
 * @author Lambert
 * @since 2022-04-22
 */
@Data
@NoArgsConstructor
@TableName("statistics_daily")
@ApiModel(value = "StatisticsDaily对象", description = "网站统计日数据")
public class StatisticsDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("统计日期")
    private String dateCalculated;

    @ApiModelProperty("注册人数")
    private Integer registerNum;

    @ApiModelProperty("登录人数")
    private Integer loginNum;

    @ApiModelProperty("每日播放视频数")
    private Integer videoViewNum;

    @ApiModelProperty("每日新增课程数")
    private Integer courseNum;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date gmtModified;

    public StatisticsDaily(String dateCalculated,Integer registerNum,
                           Integer loginNum,Integer videoViewNum,
                           Integer courseNum){
        this.dateCalculated = dateCalculated;
        this.registerNum = registerNum;
        this.loginNum = loginNum;
        this.videoViewNum = videoViewNum;
        this.courseNum = courseNum;
    }


}
