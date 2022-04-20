package com.geek.commonutils.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommentMemberInfo
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/20 14:09
 * @Version 1.0
 **/

@Data
@ApiModel(value = "CommentMemberInfo",description = "评论功能用户信息封装对象")
public class CommentMemberInfo {

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户手机号码")
    private String mobile;
}
