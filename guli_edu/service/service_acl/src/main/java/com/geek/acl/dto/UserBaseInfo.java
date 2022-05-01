package com.geek.acl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserBaseInfo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/5/1 15:03
 * @Version 1.0
 **/
@Data
@ApiModel(value="UserBaseInfo", description="用户基本信息封装对象")
public class UserBaseInfo{

    @ApiModelProperty(value = "用户名")
    private String username;
//
//    @ApiModelProperty(value = "密码")
//    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;
}
