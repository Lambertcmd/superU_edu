package com.geek.commonutils.result;


import lombok.Getter;

import java.util.Map;

@Getter
public enum ResultCodeEnum {

    SUCCESS(true,20000,"成功"),
    ERROR(false,20001,"失败");

    private Boolean success;
    private Integer code;
    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
