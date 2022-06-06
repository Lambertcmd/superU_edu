package com.geek.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName loginVo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/13 22:02
 * @Version 1.0
 **/
@Data
@ApiModel(value = "LoginVo", description = "登录对象信息")
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;

}
