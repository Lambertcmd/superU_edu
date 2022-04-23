package com.geek.eduorder.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author Lambert
 * @since 2022-04-20
 */
@Getter
@Setter
@TableName("t_pay_log")
@ApiModel(value = "PayLog对象", description = "支付日志表")
public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("支付完成时间")
    private Date payTime;

    @ApiModelProperty("支付金额（分）")
    private BigDecimal totalFee;

    @ApiModelProperty("交易流水号")
    private String transactionId;

    @ApiModelProperty("交易状态")
    private String tradeState;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    private Integer payType;

    @ApiModelProperty("其他属性")
    private String attr;

    @TableLogic
    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date gmtModified;


}
