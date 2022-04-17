package com.geek.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName RegisterVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/12 23:31
 * @Version 1.0
 **/
@Data
@ApiModel(value = "RegisterVo", description = "注册对象")
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @NotBlank
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank
    @ApiModelProperty(value = "验证码")
    private String code;
}
